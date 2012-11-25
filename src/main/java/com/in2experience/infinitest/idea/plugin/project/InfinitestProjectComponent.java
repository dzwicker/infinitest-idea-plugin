package com.in2experience.infinitest.idea.plugin.project;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileTask;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.in2experience.infinitest.idea.plugin.InfinitestApplicationPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 25.11.12
 * Time: 11:44
 */
public class InfinitestProjectComponent implements ProjectComponent, CompileTask {

	private static final Logger logger = Logger.getInstance(InfinitestProjectComponent.class);

	public static final String INFINITEST_PROJECT_COMPONENT = "InfinitestProjectComponent";

	private final Project project;

	public InfinitestProjectComponent(final Project project) {
		this.project = project;
	}

	@Override
	public void initComponent() {
		getProjectListener().projectInitialized(project);
		CompilerManager.getInstance(project).addAfterTask(this);
	}

	@Override
	public void disposeComponent() {
		getProjectListener().projectDisposed(project);
	}

	@Override
	@NotNull
	public String getComponentName() {
		return INFINITEST_PROJECT_COMPONENT;
	}

	@Override
	public void projectOpened() {
	}

	@Override
	public void projectClosed() {
	}

	private ProjectListener getProjectListener() {
		return InfinitestApplicationPlugin.getInstance();
	}

	@Override
	public boolean execute(final CompileContext context) {
		context.addMessage(CompilerMessageCategory.INFORMATION, "Run Infinitest", null, -1, -1);
		return true;
	}
}
