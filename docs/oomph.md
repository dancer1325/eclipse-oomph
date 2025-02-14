https://projects.eclipse.org/projects/tools.oomph
https://wiki.eclipse.org/Oomph

* 's goal
  * improve the user experience
* -- provides -- tools /
  * browse the Eclipse preference structure
  * maintain consistent project-specific settings | ACROSS a LARGE number of projects
  * create dynamic working sets / updates -- are added automatically as -- NEW projects | workspace
  * [targlet container](Oomph_Targlets.md)
  * manage bundle pools
  * install an IDE -- from a -- selection of project-specific configurations
  * == engine / keep an IDE -- consistent with -- its specified configuration
  * == builder / manage
    * bundle micro versions &
    * feature versions / augment PDE's API Tools
  * == small conveniences
    * Launch configuration decorators
    * Context-sensitive manifest opener
    * Copyright-consistency management
    * Project copier
    * Git command-line integration
    * Launcher for platform-specific file explorers
* basic building blocks
  * Eclipse EMF model -- for --
    * manipulating Eclipse Platform preferences
    * specifying predicate-based logical sets of projects
    * enforcing profiles of project-specific settings / -- driven by the -- predicates model
    * inducing dynamic working sets / -- driven by the -- predicates model
    * managing modular PDE target platforms / -- based on -- composable targlets
    * describing IDE configurations
* 's showcase technologies
  * [Eclipse Installer](Eclipse_Installer.md)
