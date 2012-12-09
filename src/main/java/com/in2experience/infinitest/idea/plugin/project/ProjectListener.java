package com.in2experience.infinitest.idea.plugin.project;

import com.intellij.openapi.project.Project;

public interface ProjectListener {

	void projectInitialized(final Project project);

	void projectDisposed(final Project project);
}
