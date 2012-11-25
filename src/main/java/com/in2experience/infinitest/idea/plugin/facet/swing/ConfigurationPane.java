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
package com.in2experience.infinitest.idea.plugin.facet.swing;

import static java.awt.FlowLayout.LEFT;
import static javax.swing.Box.createHorizontalBox;

import java.awt.*;

import javax.swing.*;

public class ConfigurationPane extends JPanel {

	private static final long serialVersionUID = -1L;

	public ConfigurationPane() {
		setLayout(new BorderLayout());

		final JTabbedPane pane = new JTabbedPane();
		pane.addTab("General", createGeneralTab());
		add(pane, BorderLayout.CENTER);
	}

	private Component createGeneralTab() {
		final JPanel panel = new JPanel(new FlowLayout(LEFT));

		final Box box = createHorizontalBox();

		box.add(logo());
//		box.add(createHorizontalStrut(20));

		panel.add(box);

		return panel;
	}

	private JComponent logo() {
		final ImageIcon logo = new ImageIcon(getClass().getResource("/icons/infinitestMed.png"));
		return new JLabel(logo);
	}

}
