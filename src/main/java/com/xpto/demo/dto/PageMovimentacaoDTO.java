package com.xpto.demo.dto;

import java.util.List;

public class PageMovimentacaoDTO {

	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private List<MovimentacaoDTO> results;

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

	public List<MovimentacaoDTO> getResults() {
		return results;
	}

	public void setResults(List<MovimentacaoDTO> results) {
		this.results = results;
	}
}
