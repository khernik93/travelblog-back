package com.travelblog.exception;

import com.travelblog.error.TabsError;

public class TabsException extends RuntimeException {

    public TabsException(TabsError tabsError) {
        super(tabsError.getErrorMessage());
    }

}
