package com.zhaojm.study.config.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaojm
 * @date 2020/4/8 22:18
 */
public class IntegrateRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private boolean useSuffixPatternMatch = true;

    private boolean useTrailingSlashMatch = true;

    private final List<String> fileExtensions = new ArrayList<>();

    private ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

        if(null != requestMapping){
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createRequestMappingInfo(requestMapping, methodCondition, method);
            RequestMapping typeAnnotation = AnnotatedElementUtils.findMergedAnnotation(handlerType, RequestMapping.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createRequestMappingInfo(typeAnnotation, typeCondition,handlerType).combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping, RequestCondition<?> customCondition, AnnotatedElement element) {
        String[] patterns = resolveEmbeddedValuesInPatterns(requestMapping.value());
        if(patterns!=null && (patterns.length == 0)){
            if(element instanceof Class){
                // 如果是类名就取包路径
                Class<?> handlerType = (Class<?>) element;
                patterns= new String[]{this.classPathToUrl(handlerType.getName())};
            }else{
                //如果是方法取去方法名称
                Method method = (Method) element;
                patterns= new String[]{method.getName()};
            }

        }
        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(),
                        this.useSuffixPatternMatch, this.useTrailingSlashMatch, this.fileExtensions),
                new RequestMethodsRequestCondition(requestMapping.method()),
                new ParamsRequestCondition(requestMapping.params()),
                new HeadersRequestCondition(requestMapping.headers()),
                new ConsumesRequestCondition(requestMapping.consumes(), requestMapping.headers()),
                new ProducesRequestCondition(requestMapping.produces(), requestMapping.headers(), this.contentNegotiationManager),
                customCondition);
    }

    private String classPathToUrl(String path) {
        //1.去除包路径
        //2.去除controller包
        //String typeValue = path.replaceAll("^com\\.xxxxxx\\.xxxx\\.[a-zA-z]+\\.|[c|C]ontroller[\\.]*", "");
        String typeValue = path.replaceAll("^com\\.zhaojm\\.study\\.[a-zA-z]+\\.|[c|C]ontroller[\\.]*", "");
        //3.类名称首字母转小写
        String[] typeValues = typeValue.split("\\.");
        String lastStr = typeValues[typeValues.length-1];
        typeValues[typeValues.length-1] = lastStr.substring(0, 1).toLowerCase() + lastStr.substring(1);
        return StringUtils.join(typeValues, "/");
    }
}
