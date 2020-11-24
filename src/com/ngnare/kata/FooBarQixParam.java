package com.ngnare.kata;

public class FooBarQixParam {

    /*
    Classe pour construire la Map de parametres
     */

    // Entier qui servira de diviseur et de reference
    private Integer key;

    // Modification 1
    
    //Valeur de remplacement
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FooBarQixParam(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
