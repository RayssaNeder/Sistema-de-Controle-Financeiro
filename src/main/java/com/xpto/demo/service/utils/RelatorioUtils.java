package com.xpto.demo.service.utils;

import java.util.List;

	public class RelatorioUtils {

	    public static <T> String gerarTextoRelatorio(List<T> relatorios) {
	        StringBuilder sb = new StringBuilder();
	        
	        if (!relatorios.isEmpty() && relatorios.get(0) instanceof RelatorioComFormato) {
	            sb.append("Relatório:\n");
	            for (T dto : relatorios) {
	                sb.append(((RelatorioComFormato) dto).formatarLinhaRelatorio()).append("\n");
	            }
	        } else {
	            sb.append("Nenhuma informação disponível.");
	        }

	        return sb.toString();
	    }

	    public interface RelatorioComFormato {
	        String formatarLinhaRelatorio();
	    }
	}



