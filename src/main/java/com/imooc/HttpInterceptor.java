package com.imooc;

import com.imooc.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpInterceptor
 *
 * @author yeleichao
 * @date 2018-8-20.
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) o;

            log.info("preHandle,className->{}"+handlerMethod.getBean().getClass().getName());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion,RequestHolder.remove->{}", RequestHolder.getId());
        RequestHolder.remove();
        return;
    }
}
