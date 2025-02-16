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
    * -- via -- Oomph / provides
      * [installer wizard](../user/wizard/DocInstallWizard.md)
      * [import wizard](../user/wizard/DocImportWizard.md)
      * [update wizard](../user/wizard/DocImportWizard.md)
    * 💡steps == [setup tasks](DocTask.md) / [performed AUTOMATICALLY](DocTaskExecution.md) 💡
      * ALSO, project-specific setup tasks (favorite tools, personal preferences, ...)

* TODO: 
 
 
 Oomph helps ensure that each developer works with the same uniformly-provisioned development environment
 and that each user has her personal tools and preferences uniformly available across all installations and workspaces.
 Note that Oomph can be installed into an existing IDE, i.e., one not installed by Oomph,
 via Oomph's <a rel="nofollow" href="https://download.eclipse.org/oomph/updates/latest">update site</a>
 or via Oomph's <a rel="nofollow" href="https://download.eclipse.org/oomph/updates/latest/org.eclipse.oomph.site.zip">site archive</a>.</p>
 
 <p>
 To understand how best to exploit Oomph in order to save time and spare aggravation,
 some basic concepts need to be well understood.
 Automation is achieved by specifying setup <a href="DocTask.html" title="Article in Oomph Setup Documentation">tasks</a> organized into <a href="DocScope.html" title="Article in Oomph Setup Documentation">scopes</a> stored in <a href="DocSetupResource.html" title="Article in Oomph Setup Documentation">resources</a>.
 </p>
 <p>
 An important footnote is that Oomph makes heavy use of <a href="DocBundlePool.html" title="Article in Oomph Setup Documentation">bundle pooling</a>
 so it highly disk-space efficient to install many products and provision many target platforms
 because they can all share a common bundle pool such that for each installable unit, there is only on jar file on disk.
 Not only is this space efficient,
 it also dramatically improves installation and provisioning performance
 because once an installable unit is in the pool,
 it never again needs to be downloaded from the Internet.
 </p>
 <p>
 This document provides a general overview of the concepts that underly Oomph's Setup model:
 <a href="" title="Article in Oomph Setup Documentation">Context</a>
 </p>

<p align="right">
<a href="index.html" title="Backward to Concepts"><img src="../../images/backward.png" border="0"></a>&nbsp;<a href="DocScope.html" title="Forward to Scopes"><img src="../../images/forward.png" border="0"></a></p>
<!-- <div class="help_breadcrumbs breadcrumbs_bottom"><a href="../Overview.html" title="Oomph Setup Documentation">Oomph Setup Documentation</a> > <a href="index.html" title="Category in Oomph Setup Documentation">Concepts</a></div> -->

<div class="copyright">Copyright (c) 2014 Eike Stepper (Loehne, Germany) and others.<br>All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse Public License v2.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v20.html</div>
</body>
</html>
