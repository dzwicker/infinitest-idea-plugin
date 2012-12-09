package com.in2experience.infinitest.idea.plugin.facet;

import org.jetbrains.annotations.NotNull;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeId;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.in2experience.infinitest.idea.plugin.InfinitestApplicationPlugin;

public class InfinitestFacet extends Facet<InfinitestFacetConfiguration> {

	private static final Logger logger = Logger.getInstance(InfinitestFacet.class);

	public static final FacetTypeId<InfinitestFacet> ID = new FacetTypeId<InfinitestFacet>("infinitest-plugin");

	private final Module module;

	public InfinitestFacet(@NotNull final FacetType<?, ?> facetType, @NotNull final Module module,
			final String name, @NotNull final InfinitestFacetConfiguration configuration,
			final Facet<?> underlyingFacet) {
		super(facetType, module, name, configuration, underlyingFacet);
		this.module = module;
	}

	@Override
	public void initFacet() {
		logger.debug(
				"[InfinitestPlugin]\tload facet for module '" + module.getName()
						+ "' in project '" + module.getProject().getName() + "'");
		getApplicationPlugin().facetInitialized(module);
	}

	@Override
	public void disposeFacet() {
		logger.debug("[InfinitestPlugin]\tDispose facet for module '" + module.getName()
				+ "' in project '" + module.getProject().getName() + "'");
		super.disposeFacet();
		getApplicationPlugin().facetDisposed(module);
	}

	private FacetListener getApplicationPlugin() {
		return InfinitestApplicationPlugin.getInstance();
	}
}
