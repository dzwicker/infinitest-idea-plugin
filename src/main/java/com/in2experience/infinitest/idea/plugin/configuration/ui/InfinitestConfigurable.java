package com.in2experience.infinitest.idea.plugin.configuration.ui;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.in2experience.infinitest.idea.plugin.configuration.InfinitestSettings;

/**
 * JUnitGenerator configuration UI.
 * Could be accessed via File->Settings->(IDE Settings) JUnit Generator
 *
 * @author Alex Nazimok (SCI)
 * @since <pre>Sep 7, 2004</pre>
 */
public abstract class InfinitestConfigurable implements SearchableConfigurable {

	private InfinitestSettings settings;
	private InfinitestConfiguration configuration;
	private final Project project;

	protected InfinitestConfigurable(final InfinitestSettings settings, @Nullable final Project project) {
		this.settings = settings;
		this.project = project;
	}

	/**
	 * the global configuration instance
	 */
	public static class App extends InfinitestConfigurable {

		App() {
			super(InfinitestSettings.getInstance(), null);
		}

		@NotNull
		@Override
		public String getId() {
			return "plugins.infinitest.application";
		}
	}

	/**
	 * Ask for the project specific instance, regardless of the configuration
	 */
	public static class Prj extends InfinitestConfigurable {

		Prj(final Project project) {
			super(InfinitestSettings.getProjectInstance(project), project);
		}

		@NotNull
		@Override
		public String getId() {
			return "plugins.infinitest.project";
		}
	}

	@Override
	public String getDisplayName() {
		return "Infinitest";
	}

	@Override
	@NotNull
	public String getHelpTopic() {
		return "";
	}

	@Override
	public JComponent createComponent() {
		if (configuration == null) {
			configuration = new InfinitestConfiguration(settings, project);
		}
		return configuration.getPane();
	}

	/**
	 * Compare the data to see if we are modified
	 * we are modified if we are the project settings instance and are using project settings
	 * OR we are the global instance and have a delta with the form
	 *
	 * @return true if the settings should be 'applied'
	 */
	@Override
	public boolean isModified() {
		final boolean delta = !Comparing.equal(settings.isFailCompiler(), configuration.isFailCompiler()) ||
				settings.isUseProjectSettings() != configuration.isUseProjectSettings();
		return project == null && delta ||
				project != null && configuration.isUseProjectSettings() && delta ||
				project != null && settings.isUseProjectSettings() != configuration
						.isUseProjectSettings();
	}

	@Override
	public void apply() throws ConfigurationException {
		if (configuration != null) {
			//if we have a project object and we are using project settings, say so
			settings.setUseProjectSettings(project != null && configuration.isUseProjectSettings());
			//if we are in a project and use project settings
			// or are the global instance, get them from the form
			if (project == null || settings.isUseProjectSettings()) {
				settings.setFailCompiler(configuration.isFailCompiler());
			}
		}
	}

	@Override
	public void disposeUIResources() {
		configuration = null;
	}

	@Override
	public void reset() {
		if (configuration != null) {
			configuration.setSettings(settings);
		}
	}

	@Override
	public Runnable enableSearch(final String s) {
		return null;
	}

}