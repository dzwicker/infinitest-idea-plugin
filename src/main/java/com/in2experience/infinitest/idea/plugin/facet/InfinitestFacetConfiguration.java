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

import org.jdom.Element;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;

public class InfinitestFacetConfiguration implements FacetConfiguration {

	private Module module;

	public InfinitestFacetConfiguration() {
	}

	public void setModule(final Module module) {
		this.module = module;
	}

	@Override
	public FacetEditorTab[] createEditorTabs(final FacetEditorContext editorContext,
			final FacetValidatorsManager validatorsManager) {
		return new FacetEditorTab[] {new InfinitestFacetEditorTab(this)};
	}

	@Override
	@Deprecated
	public void readExternal(final Element element) throws InvalidDataException {
	}

	@Override
	@Deprecated
	public void writeExternal(final Element configElement) throws WriteExternalException {
	}

}
