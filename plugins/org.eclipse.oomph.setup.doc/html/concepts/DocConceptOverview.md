# Context 

* | set up a fresh Eclipse development environment / work with the source code of a project, 
  * phases
    * bootstrap phase
      * == install a functional product | disk
        * install == download + unzip + run executable
    * activities | running product
      * install additional tools / NOT included | downloaded product
      * configure preferences
      * import workspace projects
      * activate the required target platform
  * MANUALLY,
    * steps
      * install a project-specific IDE / appropriate tooling (edit, compile, debug, test)
      * set workspace / project's bundles & features -- by --
        * from various source code repositories -- import -- their workspace projects
        * organize workspace projects | meaningful working sets
      * set the target platform / contain ALL the bundles and features -- to --
        * compile the project's source code,
        * run the project's tests,
        * exercise the project's end-user functionality
    * CONS
      * steps must be WELL-documented,
      * can change from release to release
      * tedious,
      * error-prone,
      * time consuming
  * AUTOMATICALLY
    * -- via -- Oomph / 
      * provides
        * [installer wizard](../user/wizard/DocInstallWizard.md)
        * [import wizard](../user/wizard/DocImportWizard.md)
        * [update wizard](../user/wizard/DocImportWizard.md)
      * makes use of [bundle pooling](DocBundlePool.md)
    * 💡steps == [setup tasks](DocTask.md) / 
      * [performed AUTOMATICALLY](DocTaskExecution.md) 💡
        * ALSO, project-specific setup tasks (favorite tools, personal preferences, ...)
        * -> 💡SAME uniformly-provisioned development environment / EACH developer 💡
      * organized into [scopes](DocScope.md)
      * stored | [resources](DocSetupResource.md)
    * | EXISTING IDE / NOT installed by Oomph, 
      * you can install Oomph -- via --
        * [Oomph's update site](https://download.eclipse.org/oomph/updates/latest) or
        * [Oomph's site archive](https://download.eclipse.org/oomph/updates/latest/org.eclipse.oomph.site.zip)
