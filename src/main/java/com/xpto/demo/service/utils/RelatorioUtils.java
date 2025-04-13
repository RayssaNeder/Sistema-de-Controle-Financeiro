package com.xpto.demo.service.utils;

import java.util.List;

public class RelatorioUtils {

	public static String gerarTextoRelatorio(Object relatorio) {
	    StringBuilder sb = new StringBuilder();
	    
	    if (relatorio == null) {
	        return "Nenhuma informação disponível.";
	    }
	    
	    if (relatorio instanceof List<?> lista) {
	        if (!lista.isEmpty() && lista.get(0) instanceof RelatorioComFormato) {
	            sb.append("Relatório:\n");
	            for (Object item : lista) {
	                sb.append(((RelatorioComFormato) item).formatarLinhaRelatorio()).append("\n");
	            }
	        } else {
	            sb.append("Nenhuma informação disponível na lista.");
	        }
	    } 
	    else if (relatorio instanceof RelatorioComFormato formato) {
	        sb.append(formato.formatarLinhaRelatorio());
	    }
	    else {
	        sb.append("Tipo de relatório não suportado: ").append(relatorio.getClass().getSimpleName());
	    }
	    
	    return sb.toString();
	}

	public interface RelatorioComFormato {
	    String formatarLinhaRelatorio();
	}


	
}
