package com.bilgeadam.language;

public class LanguageCreator {


    public static ALanguage getLanguage(Language language){
        switch (language){
            case TR: return new Tr();
            case EN: return new En();
            case FR: return new Fr();
            case DE: return new De();
            default: return new En();
        }
    }
}
