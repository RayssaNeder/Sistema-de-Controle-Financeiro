package com.xpto.demo.dto;

	import java.util.List;

	public class PageEnderecoDTO {

	    private int page;
	    private int size;
	    private long totalElements;
	    private int totalPages;
	    private List<EnderecoDTO> results;

	    // Getters e Setters

	    public int getPage() {
	        return page;
	    }

	    public void setPage(int page) {
	        this.page = page;
	    }

	    public int getSize() {
	        return size;
	    }

	    public void setSize(int size) {
	        this.size = size;
	    }

	    public long getTotalElements() {
	        return totalElements;
	    }

	    public void setTotalElements(long totalElements) {
	        this.totalElements = totalElements;
	    }

	    public int getTotalPages() {
	        return totalPages;
	    }

	    public void setTotalPages(int totalPages) {
	        this.totalPages = totalPages;
	    }

	    public List<EnderecoDTO> getResults() {
	        return results;
	    }

	    public void setResults(List<EnderecoDTO> results) {
	        this.results = results;
	    }
	}

