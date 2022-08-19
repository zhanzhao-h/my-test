package com.test.demo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author zhan.zhao
 */
@Slf4j
public class ValidateUtil {
    private ValidateUtil() {
    }

    private static Validator validator;

    static {
        //初始化检查器。
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     *  代替@Valid 注解,获取校验参数的结果.  有一个校验未通过,立即返回.
     * 返回"" 校验通过.否则未通过并返回错误信息.
     * @author zhan.zhao
    */
    public static String validateParam(Object object){
        Set<ConstraintViolation<Object>> validateResultSet = validator.validate(object);
        if (CollectionUtils.isEmpty(validateResultSet)) {
            return "";
        }else {
            log.info("[{}] ok to validateParam_o34iunyh98. ",HeaderUtil.getTransactionId());
           return validateResultSet.iterator().next().getMessage();
        }
    }
}
