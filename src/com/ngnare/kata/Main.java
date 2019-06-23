package com.ngnare.kata;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        FooBarQixParam foo = new FooBarQixParam(3, "Foo");
        FooBarQixParam bar = new FooBarQixParam(5, "Bar");
        FooBarQixParam qix = new FooBarQixParam(7, "Qix");

        Map<Integer, String> contentCheckParams = getParamsMap(foo, bar, qix);
        Map<Integer, String> divisorCheckParams = getParamsMap(foo, bar);


        for (int i = 1; i <= 100;i++){
            System.out.println( i +" -> "+transform(i,contentCheckParams,divisorCheckParams));
        }

    }

    /*

     */
    public static String transform(int entier, Map<Integer, String> contentCheckParams, Map<Integer, String> divisorCheckParams) {
        if (entier == 0) {
            return "0";
        }

        // On applique d'abord la trasnformation par "diviseur", en utilisant une Map avec les paramètres dédiés
        String divResult = replaceByDivisor(entier, divisorCheckParams);

        String valStr = String.valueOf(entier);

        // Puis On applique la trasnformation par "content", en utilisant une Map avec les paramètres dédiés
        String contentResult = replaceByContent(valStr, contentCheckParams);

        //On construit une string concaténant les deux transformations
        StringBuilder result = new StringBuilder();
        result.append(divResult != null ? divResult : "").append(contentResult != null ? contentResult : "");

        //On retourne le résultat des trasnformations, s'il est non vide, sinon on retourne l'entier
        return result.toString().isEmpty() ? valStr : result.toString();
    }

    /*
    On construit un Map<FooBarQixParam.key,FooBarQixParam.value> à partir du tableau de Paramètres en arguments
     */
    public static Map<Integer, String> getParamsMap(FooBarQixParam... params) {
        Map<Integer, String> foobar = Arrays.asList(params).stream().filter(t -> t.getKey() != null && t.getValue() != null).collect(Collectors.toMap(FooBarQixParam::getKey, FooBarQixParam::getValue));
        return foobar;
    }

    /*
    On décompose l'entier en n-chars. Et pour chaque "char", on récupére la "vaue" associée dans la map
    et on crée une seule String à partir de la collection de String obtenues
     */
    private static String replaceByContent(String entier, Map<Integer, String> params) {
        return entier.chars()
                .mapToObj(i -> params.getOrDefault(Character.getNumericValue(i), ""))
                .collect(Collectors.joining());
    }

    /*
    A partir de la Map de parametres, Quand la "key" est diviseur  de "entier":
    On retourne la "value" associée dans la map
    et on crée une seule String à partir de la collection de String obtenues
     */
    private static String replaceByDivisor(int entier, Map<Integer, String> params) {
        return params.keySet().stream()
                .filter(base -> (base != 0 && entier % base == 0))
                .map(params::get)
                .collect(Collectors.joining());
    }


}
