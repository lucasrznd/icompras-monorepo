package io.github.lucasrznd.icompras.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class StringFormatUtil {

    public static String formatData(String createdAt, DateTimeFormatter formatter) {
        return LocalDateTime.parse(createdAt.replace(" ", "T")).format(formatter);
    }

    public static String formatCpf(String cpf) {
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatTelefone(String telefone) {
        return telefone.length() == 11
                ? telefone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3")
                : telefone.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
    }
}
