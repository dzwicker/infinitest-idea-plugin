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

import org.jetbrains.annotations.Nls;

import com.intellij.facet.ui.FacetEditorTab;
import com.in2experience.infinitest.idea.plugin.facet.swing.ConfigurationPane;

public class InfinitestFacetEditorTab extends FacetEditorTab {

	private final InfinitestFacetConfiguration configuration;
	private final ConfigurationPane configurationPane = new ConfigurationPane();

	public InfinitestFacetEditorTab(final InfinitestFacetConfiguration configuration) {
		this.configuration = configuration;
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Infinitest";
	}

	@Override
	public JComponent createComponent() {
		return configurationPane;
	}

	@Override
	public boolean isModified() {
		return false;
	}

	@Override
	public void apply() {
	}

	@Override
	public void reset() {
	}

	@Override
	public void disposeUIResources() {
	}

}