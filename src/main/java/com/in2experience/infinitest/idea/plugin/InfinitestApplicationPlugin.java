package com.in2experience.infinitest.idea.plugin;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.in2experience.infinitest.idea.plugin.facet.FacetListener;
import com.in2experience.infinitest.idea.plugin.project.ProjectListener;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 24.11.12
 * Time: 19:22
 */

public class InfinitestApplicationPlugin
		implements ApplicationComponent, ProjectListener, FacetListener {

	private static final String IDEAINFINITEST_COMPONENT_NAME = "InfinitestApplicationPlugin";

	private static final Logger logger = Logger.getInstance(InfinitestApplicationPlugin.class);

	private final Application application;

	private final Set<Project> projects = new HashSet<>();

	private Table<Project, Module, String> projectModules = HashBasedTable.create();

	/**
	 * Creates the Infinitest Plugin
	 */
	public InfinitestApplicationPlugin(final Application application) {
		this.application = application;
		logger.info("[InfinitestPlugin]\tCreate InfinitestPlugin");
	}

	public static InfinitestApplicationPlugin getInstance() {
		return (InfinitestApplicationPlugin) ApplicationManager.getApplication()
				.getComponent(IDEAINFINITEST_COMPONENT_NAME);
	}

	@Override
	public void initComponent() {
		logger.info("[InfinitestPlugin]\tinit for application '" + application.toString() + "'");
		setupListener();
		logger.info("[InfinitestPlugin]\tdone");
	}

	@Override
	public void disposeComponent() {
	}

	@NotNull
	@Override
	public String getComponentName() {
		return IDEAINFINITEST_COMPONENT_NAME;
	}

	private void setupListener() {
		// Since the Vim plugin custom actions aren't available to the call to <code>initComponent()</code>
		// we need to force the generation of the key map when the first project is opened.
//		ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerAdapter() {
//
//			@Override
//			public void projectOpened(final Project project) {
//			}
//
//			@Override
//			public void projectClosed(final Project project) {
//			}
//		});
//
//		CommandProcessor.getInstance().addCommandListener(DelegateCommandListener.getInstance());
	}

	@Override
	public void projectInitialized(final Project project) {
		projects.add(project);
		logger.info("[InfinitestPlugin]\tadd project '" + project.getName() + "'");
	}

	@Override
	public void projectDisposed(final Project project) {
		projects.remove(project);
		logger.info("[InfinitestPlugin]\tremove project '" + project.getName() + "'");
	}

	@Override
	public void facetInitialized(final Module module) {
		final Project project = module.getProject();
		projectModules.put(project, module, module.getName());
		logger.info("[InfinitestPlugin]\tadd module '" + module.getName()
				+ "' in project '" + project.getName() + "'");
	}

	@Override
	public void facetDisposed(final Module module) {
		final Project project = module.getProject();
		projectModules.remove(project, module);
		logger.info("[InfinitestPlugin]\tremove module '" + module.getName()
				+ "' in project '" + project.getName() + "'");
	}

}
