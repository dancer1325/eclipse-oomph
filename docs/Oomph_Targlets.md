https://wiki.eclipse.org/Oomph_Targlets

* Oomph's Targlet technology
  * -- extends -- [Eclipse PDE](https://projects.eclipse.org/projects/eclipse.pde)'s base functionality /
    * specify a target platform definition 
    * mechanism, MORE
      * expressive,
      * flexible,
      * scalable,
      * reliable 
    * FULLY integrated -- with -- PDE
    * uses PDE's target location extension point
    * augment PDE's built-in target location implementations
      * -- via providing a -- Targlet Container implementation

* Oomph's Targlet Container vs PDE's Software Site container
  * way to express dependencies
    * | Targlet Container, requirements
      * can specify
        * version range,
        * optionality,
        * greediness,
        * cardinality,
        * filters,
        * p2 namespace
    * | PDE's Software Site container, Installable Units
      * requires
        * specify NO version or
        * exact version
  * TODO:Dependencies of local projects in the file system, e.g., in a local Git clone, can be taken into account during resolution using a Source Locator.
    * A Targlet Container resolves the requirements from the combination of all local projects and all available software sites.
    * It can use wildcards to specify all available Requirements of the Source Locator.
    * It imports projects into the workspace as a side-effect of resolution.
  * The resolution process supports roll-back on failure.
    * A resolution failure or network failure with Targlets will leave the old successful resolution in place.
    * A resolution failure with PDE's Software Site container will destroy your target platform, leaving all your workspace projects with errors until the problem is resolved, e.g., until your network or the hosting servers of the software sites are restored.
  * Resolved artifacts use a shared bundle pool, which is highly significant when you have multiple workspace.
    * Targlet Containers will download and store each artifact once in the file system, sharing them across workspaces (and even sharing the artifacts used by your installations).
    * PDE resolves artifacts separately into a workspace-specific bundle pool, resulting in many duplicates (and downloading artifacts already available in your installation).
  * Update sites can be grouped into named lists, so that a single target definition can be resolved against different sets of repositories by switching to different named Repository Lists. This is most useful in combination with a Targlet Task that can easily select the active Repository List.

* TODO: