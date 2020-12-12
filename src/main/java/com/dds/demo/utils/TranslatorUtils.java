package com.dds.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TranslatorUtils {

    private static ResourceBundleMessageSource messageSource;
    private static final Logger log = LoggerFactory.getLogger(TranslatorUtils.class);

    @Autowired
    TranslatorUtils(ResourceBundleMessageSource messageSource) {
        TranslatorUtils.messageSource = messageSource;
    }

    public static String toLocaleWithDefault(String msgCode, String defaultMessage, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, defaultMessage, locale);
    }

    public static String toLocaleWithDefault(String msgCode, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, defaultMessage, locale);
    }

    public static String toLocale(String msgCode, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String toLocale(String msgCode) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(msgCode, null, locale);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "";
        }
    }

}
