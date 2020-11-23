package jy.study.springbootrest.model.user.exception;

import lombok.Getter;

@Getter
public class UserException extends Exception {

    public enum UserExceptionType {
        DUPLICATION("중복된 USER"),
        ;

        private String msg;

        public String getMsg() {
            return msg;
        }

        UserExceptionType(String msg) {
            this.msg = msg;
        }
    }

    private UserExceptionType exceptionType;

    public UserException(UserExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String errMsg() {
        if(exceptionType == UserExceptionType.DUPLICATION)
            return UserExceptionType.DUPLICATION.msg;

        return "";
    }

}
