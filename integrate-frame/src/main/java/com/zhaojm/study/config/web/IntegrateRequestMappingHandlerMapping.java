package com.zhaojm.study.config.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * @author zhaojm
 * @date 2020/4/8 22:18
 */
public class IntegrateRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private final static String PACKAGE_NAME = "com.zhaojm.study";

    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = null;
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

        if(null != requestMapping){
            info = createRequestMappingInfo(requestMapping, method);
            RequestMapping typeAnnotation = AnnotatedElementUtils.findMergedAnnotation(handlerType, RequestMapping.class);
            if (typeAnnotation != null) {
                info = createRequestMappingInfo(typeAnnotation, handlerType).combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping, AnnotatedElement element) {
        if(null == requestMapping) {
            return null;
        }
        RequestCondition<?> customCondition = null;
        String[] patterns = resolveEmbeddedValuesInPatterns(requestMapping.path());
        if(null != patterns && patterns.length == 0){
            if(element instanceof Class){
                // 如果是类名就取包路径
                customCondition = getCustomTypeCondition((Class<?>) element);
                Class<?> handlerType = (Class<?>) element;
                if(handlerType.getName().startsWith(PACKAGE_NAME)) {
                    patterns = new String[]{this.classPathToUrl(handlerType.getName())};
                }
            }else{
                //如果是方法取去方法名称
                Method method = (Method) element;
                customCondition = getCustomMethodCondition((Method) element);
                if(method.getDeclaringClass().getName().startsWith(PACKAGE_NAME)) {
                    patterns = new String[]{method.getName()};
                }
            }
        }
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(patterns))
                .methods(requestMapping.method())
                .params(requestMapping.params())
                .headers(requestMapping.headers())
                .consumes(requestMapping.consumes())
                .produces(requestMapping.produces())
                .mappingName(requestMapping.name());
        if (customCondition != null) {
            builder.customCondition(customCondition);
        }
        return builder.options(this.config).build();
    }

    private String classPathToUrl(String path) {
        //1.去除包路径  2.去除controller包 3.类名称首字母转小写
        //String typeValue = path.replaceAll("^com\\.xxxxxx\\.xxxx\\.[a-zA-z]+\\.|[c|C]ontroller[\\.]*", "");
        String typeValue = path.replaceAll("^com\\.zhaojm\\.study\\.[a-zA-z]+\\.|[c|C]ontroller[.]*", "");
        String[] typeValues = typeValue.split("\\.");
        String lastStr = typeValues[typeValues.length - 1];
        typeValues[typeValues.length - 1] = lastStr.substring(0, 1).toLowerCase() + lastStr.substring(1);
        return StringUtils.join(typeValues, "/");
    }
}
