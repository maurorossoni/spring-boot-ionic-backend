package com.nelioalves.cursomc.services.validation.utils;


public class BR {

    public static boolean IsValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]+", ""); // remove caracteres não numéricos
        if (cpf.length() != 11) {
            return false;
        }

        // calcula primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        // calcula segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        return Character.getNumericValue(cpf.charAt(9)) == digito1 && Character.getNumericValue(cpf.charAt(10)) == digito2;
    }

    public static boolean IsValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]+", ""); // remove caracteres não numéricos
        if (cnpj.length() != 14) {
            return false;
        }

        // calcula primeiro dígito verificador
        int soma = 0;
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }
        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        // calcula segundo dígito verificador
        soma = 0;
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }
        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        return Character.getNumericValue(cnpj.charAt(12)) == digito1 && Character.getNumericValue(cnpj.charAt(13)) == digito2;
    }
}