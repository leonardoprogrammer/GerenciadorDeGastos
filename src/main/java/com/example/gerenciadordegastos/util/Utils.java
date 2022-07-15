package com.example.gerenciadordegastos.util;

import java.io.File;
import java.text.Collator;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Utils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isNullOrEmpty(Character value) {
        return value == null || value.equals(' ');
    }

    public static boolean isNullOrEmpty(Map value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNullOrEmpty(List value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNullOrEmpty(Set value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNullOrEmpty(Object[] value) {
        return value == null || value.length == 0;
    }

    public static boolean isNullOrZero(Integer value) {
        return value == null || value == 0;
    }

    public static boolean isNullOrVazia(List<Object> listObject) {
        return listObject == null || listObject.isEmpty();
    }

    public static boolean isNullOrZero(Double value) {
        return value == null || value == 0;
    }

    public static boolean isNullOrZero(Long value) {
        return value == null || value == 0;
    }

    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        return (diferenca / 1000) / 60 / 60 / 24;
    }

    public static boolean valida_cpf(String cpf) {
        boolean digitos_iguais = true;
        int soma, resultado;
        String numeros, digitos;
        if (cpf.length() != 11) { // tem que ter 11 caracteres
            return false;
        }
        // nao pode ter dois digitos consecutivos iguais como 11, 22, 33
        for (int i = 0; i < cpf.length() - 1; i++) {
            if (cpf.charAt(i) != cpf.charAt(i + 1)) {
                digitos_iguais = false;
                break;
            }
        }
        if (!digitos_iguais) {
            numeros = cpf.substring(0, 9); // o numero sem o digito verificador
            digitos = cpf.substring(9); // os digitos verificadores
            soma = 0;
            // realiza o somatorio :
            // (numero0 * 10) + (numero1 * 9) + ...+ (numero8 * 2)
            for (int i = 10; i > 1; i--) {
                soma += Integer.parseInt(String.valueOf((numeros.charAt(10 - i)))) * i;
            }
            /*
             * pega o resta da divisao  se for menor que 2, resultado 0, senao
             * eh 11 - soma % 11
             */
            resultado = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
            /*
             * Codigo Validacao CPF_CNPJ  O resultado da conta anterior o
             * primeiro digito verificador
             */
            if (resultado != Integer.parseInt(String.valueOf((digitos.charAt(0))))) {
                return false;
            }
            // o novo numero é o cpf + o primeiro digito verificador
            numeros = cpf.substring(0, 10);
            soma = 0;
            // realiza o somatorio :
            // (numero0 * 11) + (numero1 * 10) + ...+ (numero9 * 2)
            for (int i = 11; i > 1; i--) {
                soma += Integer.parseInt(String.valueOf((numeros.charAt(11 - i)))) * i;
            }
            resultado = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
            return resultado == Integer.parseInt(String.valueOf((digitos.charAt(1))));
        } else {
            return false;
        }
    }

    public static boolean valida_cnpj(String cnpj) {
        boolean digitos_iguais = true;
        int soma;
        int resultado;
        int tamanho;
        int pos;
        String numeros, digitos;
        // checa o tamanho
        if (cnpj.length() != 14) {
            return false;
        }

        //digitos vizinho devem ser diferentes
        for (int i = 0; i < cnpj.length() - 1; i++) {
            if (cnpj.charAt(i) != cnpj.charAt(i + 1)) {
                digitos_iguais = false;
                break;
            }
        }
        if (!digitos_iguais) {
            tamanho = cnpj.length() - 2;
            numeros = cnpj.substring(0, tamanho); // o cnpj propriamente dito
            digitos = cnpj.substring(tamanho); //os digitos verificadores
            soma = 0;
            pos = tamanho - 7;

            /*
             * Executa o calculo: (numeto0 * 5) + ( numero1 * 4) + ( numero2 *
             * 3) + ( numero3 * 2) + ( numero4 * 9) + ( numero5 * 8)+ ( numero6
             * * 7) + ( numero7 * 6) + ( numero8 * 5) + ( numero9 * 4)+ (
             * numero10 * 3) + ( numero11 * 2)
             *
             */
            for (int i = tamanho; i >= 1; i--) {
                soma += Integer.parseInt(String.valueOf((numeros.charAt(tamanho - i)))) * pos--;
                if (pos < 2) {
                    pos = 9;
                }
            }

            // o resultado pela o resta da divisao por 11, se for menor que 2 zero
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

            // compara o resultado com o primerio digito verificador
            if (resultado != Integer.parseInt(String.valueOf((digitos.charAt(0))))) {
                return false;
            }
            tamanho = tamanho + 1;

            //o cnpj agora incluindo o primeiro digito verificador
            numeros = cnpj.substring(0, tamanho);
            soma = 0;
            pos = tamanho - 7;

            /*
             * Executa o calculo: (numero0 * 6) + (numero1 * 5) + ( numero2 * 4)
             * + ( numero3 * 3) + ( numero4 * 2) +( numero5 * 9) + ( numero6 *
             * 8)+ ( numero7 * 7) + ( numero8 * 6) +( numero9 * 5) + ( numero10
             * * 4)+ ( numero11 * 3) + ( numero12 * 2)
             *
             */
            for (int i = tamanho; i >= 1; i--) {
                soma += Integer.parseInt(String.valueOf((numeros.charAt(tamanho - i)))) * pos--;
                if (pos < 2) {
                    pos = 9;
                }
            }

            // o resultado pela o resta da divisao por 11, se for menor que 2 zero
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

            return resultado == Integer.parseInt(String.valueOf((digitos.charAt(1))));
        } else {
            return false;
        }
    }

    public static Calendar converterLocalDateParaCalendar(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar;
    }

    public static Date recuperarProximoDiaSemana(Date dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                break;
            case Calendar.SATURDAY:
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);
                break;
            default:
        }
        return calendar.getTime();
    }

    // O método <compararString compara duas string ignorando
    //     * maiuscula/minuscula e acentuação.
    public static boolean compararString(String str1, String str2) {
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        collator.setStrength(Collator.PRIMARY); // importante!
        if (collator.compare(str1, str2) == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static Date recuperarPrazoPeloDiadoMes(int diaVencimento, Date dataEmissao) {
        Calendar dtEmissaoCal = Calendar.getInstance();
        dtEmissaoCal.setTime(dataEmissao);
        dtEmissaoCal.set(Calendar.HOUR_OF_DAY, 0);
        dtEmissaoCal.set(Calendar.MINUTE, 0);
        dtEmissaoCal.set(Calendar.SECOND, 0);
        dtEmissaoCal.set(Calendar.MILLISECOND, 0);

        Calendar dtVenc = Calendar.getInstance();
        dtVenc.set(Calendar.MILLISECOND, 0);
        dtVenc.set(dtEmissaoCal.get(Calendar.YEAR), dtEmissaoCal.get(Calendar.MONTH), diaVencimento, 0, 0, 0);
        dtVenc.add(Calendar.MONTH, 1);

        return dtVenc.getTime();
    }

    public static Date getDataAtualSemHora() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static int getAnoDeUmaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMesDeUmaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.MONTH);
    }

    public static boolean isPrimeiroSemestre(){
        return !isSegundoSemestre();
    }

    public static boolean isSegundoSemestre(){
        return getDataAtualSemHora().after(getDataInicioSegundoSemestre());
    }

    public static Date getDataInicioSegundoSemestre(){
        Calendar segundoSemestreCal = Calendar.getInstance();
        segundoSemestreCal.set(segundoSemestreCal.get(Calendar.YEAR), 6, 1, 0, 0, 0); //01/07 segundo semestre
        segundoSemestreCal.set(Calendar.MILLISECOND, 0);
        return segundoSemestreCal.getTime();
    }

    public static Date getDataInicioPrimeiroSemestre(){
        Calendar primeiroSemestreCal = Calendar.getInstance();
        primeiroSemestreCal.set(primeiroSemestreCal.get(Calendar.YEAR), 0, 1, 0, 0, 0); //01/01 primeiro semestre
        primeiroSemestreCal.set(Calendar.MILLISECOND, 0);
        return primeiroSemestreCal.getTime();
    }

    public static String pegarNomeDoArquivo(String diretorio, boolean comExtensao) {
        if (diretorio != null || diretorio.isEmpty()) {
            return "";
        }

        String nomeArquivo = "";

        if (diretorio.contains("\\")) {
            String[] diretorioSplit = diretorio.split("\\\\");
            if (comExtensao) {
                nomeArquivo = diretorioSplit[diretorioSplit.length - 1];
            } else {
                String[] aux = diretorioSplit[diretorioSplit.length - 1].split("\\.");
                nomeArquivo = aux[0];
            }
            return nomeArquivo;
        } else {
            return diretorio;
        }
    }

    public static String pegarCaminhoDoArquivo(String diretorio) {
        if (diretorio == null || diretorio.isEmpty()) {
            return "";
        }

        String caminho = "";

        if (diretorio.contains("\\")) {
            String[] caminhoSplit = diretorio.split("\\\\");
            for (int i = 0; i < (caminhoSplit.length - 1); i++) {
                if (i != 0) {
                    caminho += "\\";
                }
                caminho += caminhoSplit[i];
            }
            return caminho;
        } else {
            return diretorio;
        }
    }

    public static File pegarCaminho(File arquivo) {
        if (arquivo == null || arquivo.getPath().isEmpty()) {
            return null;
        }

        String diretorio = arquivo.getPath();
        String caminho = "";

        if (diretorio.contains("\\")) {
            String[] caminhoSplit = diretorio.split("\\\\");
            for (int i = 0; i < (caminhoSplit.length - 1); i++) {
                if (i != 0) {
                    caminho += "\\";
                }
                caminho += caminhoSplit[i];
            }
            return new File(caminho);
        } else {
            return arquivo;
        }
    }

}
