package com.in2experience.infinitest.idea.plugin.facet;

import com.intellij.openapi.module.Module;

public interface FacetListener {

	void facetInitialized(final Module module);

	void facetDisposed(final Module module);
}
