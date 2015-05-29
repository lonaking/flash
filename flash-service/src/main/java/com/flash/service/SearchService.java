package com.flash.service;

import java.util.List;

import com.flash.base.tool.page.Page;

public interface SearchService<T> {

	public Page<List<T>> search();
}
