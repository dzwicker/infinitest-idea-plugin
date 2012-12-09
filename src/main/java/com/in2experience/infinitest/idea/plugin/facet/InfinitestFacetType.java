/*
 * This file is part of Infinitest.
 *
 * Copyright (C) 2010
 * "Ben Rady" <benrady@gmail.com>,
 * "Rod Coffin" <rfciii@gmail.com>,
 * "Ryan Breidenbach" <ryan.breidenbach@gmail.com>, et al.
 *
 * Infinitest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Infinitest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Infinitest.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.in2experience.infinitest.idea.plugin.facet;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.util.IconLoader;
import com.in2experience.infinitest.idea.plugin.InfinitestUtil;

public class InfinitestFacetType extends FacetType<InfinitestFacet, InfinitestFacetConfiguration> {

	public static final InfinitestFacetType INSTANCE = new InfinitestFacetType();

	private InfinitestFacetType() {
		super(InfinitestFacet.ID, "InfinitestPlugin", "Infinitest-Plugin");
	}

	@Override
	public InfinitestFacetConfiguration createDefaultConfiguration() {
		return new InfinitestFacetConfiguration();
	}

	@Override
	public InfinitestFacet createFacet(@NotNull Module module, String name,
			@NotNull InfinitestFacetConfiguration configuration, @Nullable
	Facet underlyingFacet) {
		configuration.setModule(module);
		return new InfinitestFacet(this, module, name, configuration, underlyingFacet);
	}

	@Override
	public Icon getIcon() {
		return InfinitestUtil.ICON;
	}

	@Override
	public boolean isSuitableModuleType(ModuleType moduleType) {
		return StdModuleTypes.JAVA.equals(moduleType) || "PLUGIN_MODULE".equals(moduleType.getId());
	}
}
