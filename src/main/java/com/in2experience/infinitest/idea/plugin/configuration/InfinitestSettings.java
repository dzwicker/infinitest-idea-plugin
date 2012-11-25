package com.in2experience.infinitest.idea.plugin.configuration;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;

@State(
		name = "InfinitestSettings",
		storages = {
				@Storage(id = "prj-default", file = "$PROJECT_FILE$"),
				@Storage(id = "dir", file = "$PROJECT_CONFIG_DIR$/infinitest-settings.xml",
						scheme = StorageScheme.DIRECTORY_BASED)})
public class InfinitestSettings implements PersistentStateComponent<InfinitestSettings> {

	private static final Logger logger = Logger.getInstance(InfinitestSettings.class);

	@State(
			name = "InfinitestSettings",
			storages = {
					@Storage(id = "app-default", file = "$APP_CONFIG$/infinitest-settings.xml")})
	public static class App extends InfinitestSettings {

		public App() {
			loadDefaultState(this);
		}
	}

	@State(
			name = "InfinitestSettings",
			storages = {
					@Storage(id = "prj-default", file = "$PROJECT_FILE$"),
					@Storage(id = "dir", file = "$PROJECT_CONFIG_DIR$/infinitest-settings.xml",
							scheme = StorageScheme.DIRECTORY_BASED)})
	public static class Prj extends InfinitestSettings {

		public Prj() {
			//load the default state from the application state
			loadDefaultState(this);
		}
	}

	/**
	 * Return an instance from the service manager
	 *
	 * @return the settings instance
	 */
	public static InfinitestSettings getInstance() {
		return ServiceManager.getService(InfinitestSettings.class);
	}

	/**
	 * Return the proper settings based on the configuration of the project. If the project settings
	 * are not used, this method returns the global settings instance
	 *
	 * @param project the project
	 * @return the settings instance
	 */
	public static InfinitestSettings getInstance(final Project project) {
		InfinitestSettings settings = getProjectInstance(project);
		if (!settings.isUseProjectSettings()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Project is configured for global settings, so we return the global settings");
			}
			settings = getInstance();
		}
		return settings;
	}

	/**
	 * Return the project settings, regardless of the configuration
	 *
	 * @param project the project
	 * @return the settings instance
	 */
	public static InfinitestSettings getProjectInstance(final Project project) {
		return ServiceManager.getService(project, InfinitestSettings.class);
	}

	@Override
	public InfinitestSettings getState() {
		return this;
	}

	@Override
	public void loadState(final InfinitestSettings settings) {
		XmlSerializerUtil.copyBean(settings, this);
	}

	/**
	 * Load the default state
	 *
	 * @param settings the settings to load into
	 */
	public static void loadDefaultState(final InfinitestSettings settings) {
		logger.debug("setting up default configuration");
		settings.setFailCompiler(true);
		settings.setUseProjectSettings(false);
	}

	private boolean failCompiler;
	private boolean useProjectSettings;

	public boolean isFailCompiler() {
		return failCompiler;
	}

	public void setFailCompiler(final boolean failCompiler) {
		this.failCompiler = failCompiler;
	}

	public boolean isUseProjectSettings() {
		return useProjectSettings;
	}

	public void setUseProjectSettings(final boolean useProjectSettings) {
		this.useProjectSettings = useProjectSettings;
	}
}
