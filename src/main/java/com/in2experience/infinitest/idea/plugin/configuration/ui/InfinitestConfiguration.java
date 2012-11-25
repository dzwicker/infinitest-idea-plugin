package com.in2experience.infinitest.idea.plugin.configuration.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.in2experience.infinitest.idea.plugin.configuration.InfinitestSettings;

/**
 * @author JOsborne
 * @since 1/4/12 2:44 PM
 */
public class InfinitestConfiguration {

	private JPanel pane;
	private JTabbedPane tabbedPane1;
	private JCheckBox failCompilerCheckBox;
	private JComboBox settingsTypeComboBox;
	private JLabel settingsTypeLabel;
	private JButton copyGobalSettingsToButton;
	private Editor velocityEditor;
	private final Project project;

	public InfinitestConfiguration(final InfinitestSettings settings, final Project project) {
		this.project = project;
		setSettings(settings);
		updateSettingsVisibility();
		settingsTypeComboBox.setVisible(this.project != null);
		settingsTypeLabel.setVisible(this.project != null);
		//if we use global settings, then hide the project settings
		settingsTypeComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				updateSettingsVisibility();
			}
		});
		copyGobalSettingsToButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				if ("copy".equals(e.getActionCommand())) {
					copyGlobalSettings();
				}
			}
		});
	}

	public JPanel getPane() {
		return pane;
	}

	public void setSettings(final InfinitestSettings settings) {
		setFailCompiler(settings.isFailCompiler());
		setUseProjectSettings(settings.isUseProjectSettings());
	}

	public boolean isUseProjectSettings() {
		return project != null && settingsTypeComboBox.getSelectedIndex() > 0;
	}

	public void setUseProjectSettings(final boolean useProjectSettings) {
		settingsTypeComboBox.setSelectedIndex(useProjectSettings ? 1 : 0);
		tabbedPane1.setVisible(project == null || useProjectSettings);
	}

	public boolean isFailCompiler() {
		return failCompilerCheckBox.isSelected();
	}

	public void setFailCompiler(final boolean failCompiler) {
		failCompilerCheckBox.setSelected(failCompiler);
	}

	public void updateSettingsVisibility() {
		tabbedPane1.setVisible(project == null || settingsTypeComboBox.getSelectedIndex() > 0);
		copyGobalSettingsToButton.setVisible(project != null);
	}

	/**
	 * copy the global settings in but maintain the 'project settings' type
	 */
	public void copyGlobalSettings() {
		final boolean currentProjectSettingsType = isUseProjectSettings();
		setSettings(InfinitestSettings.getInstance());
		setUseProjectSettings(currentProjectSettingsType);
	}
}
