# Scopes

* `org.eclipse.oomph.setup.Scope`
* == course-grained unit -- for grouping -- related [tasks](DocTask.md) /
  * hierarchically structured
  * stored | [resources](DocSetupResource.md)
* 's properties
  * `name`
    * MANDATORY
    * lower case
      * Reason: 🧠hierarchical `containment` structure of scopes -> induces a `qualified` name attribute -- based on the -- '.' scope name separator 🧠
  * `label`
    * OPTIONAL
    * title case name
  * `description`
    * OPTIONAL

## Product Catalogs
* == `org.eclipse.oomph.setup.ProductCatalog`
* ==  container -- for -- 
  * products
  * ALL tasks / install ANY of those products
* _Example:_ Eclipse product catalog -- generated for -- ALL packages available | [Eclipse's download page](https://www.eclipse.org/downloads/)
* use case
  * Oomph -- to -- install ANY package / you'd normally download and unzip | Eclipse.org
* self product catalog
  * == synthetic product catalog / 
    * Oomph installed -- BUT, NOT via -- [Oomph's install wizard](../user/wizard/DocInstallWizard.md)
* stored | [project catalog resource](DocSetupResource.md)

### Products

* `org.eclipse.oomph.setup.Product`
* == container -- for -- 
  * product versions
  * ALL [tasks](DocTask.md) / install ANY product versions
* _Example:_ product | Eclipse product catalog / EACH [Eclipse package](http://www.eclipse.org/downloads/packages/release/Luna/R)
* self product | self product catalog
  * == product / 
    * Oomph installed -- BUT, NOT via -- [Oomph's install wizard](../user/wizard/DocInstallWizard.md)
    * contains a self product version

* Product Versions
  * == `org.eclipse.oomph.setup.ProductVersion`
  * == container -- for -- ALL tasks / install a specific product's version
  * _Example:_ product version | Eclipse Standard product | Eclipse product catalog, -- for installing -- [Eclipse Standard Luna package](https://www.eclipse.org/downloads/packages/eclipse-standard-44/lunar)
  * synthetic product version | self product | self product catalog
    * requirements
      * product / Oomph installed -- BUT NOT via -- [Oomph's install wizard](../user/wizard/DocInstallWizard.md)
    * via [p2 task](DocTask.md) -- captures -- ALL
      * product installation's installable units 
      * update p2 sites / can update those units | product installation

## Project Catalogs

* == `org.eclipse.oomph.setup.Project`
* == container -- for -- 
  * projects
  * BOTH tasks / 
    * provision a workspace
    * install the tools -- needed to work with -- ANY of those projects
* _Example:_ Eclipse project catalog / provision VARIOUS projects hosted | Eclipse.org.
* stored | [project catalog resource](DocSetupResource.md)

### Projects

* TODO:
<p>
 A <a href="../../javadoc/org/eclipse/oomph/setup/Project.html" title="Interface in org.eclipse.oomph.setup"><code>project</code></a> is a <a href="../../javadoc/org/eclipse/oomph/setup/SetupTaskContainer.html#getSetupTasks()" title="Method in org.eclipse.oomph.setup.SetupTaskContainer"><code>container</code></a> for <a href="DocScope.html#DocStream" title="Chapter in Oomph Setup Documentation">streams</a>
 as well as a <a href="../../javadoc/org/eclipse/oomph/setup/SetupTaskContainer.html#getSetupTasks()" title="Method in org.eclipse.oomph.setup.SetupTaskContainer"><code>container</code></a> for both
 the tasks to provision a workspace with any of those streams
 and the tasks to install the tools needed to work with any of those streams.
 For example,
 there is a project in the Eclipse project catalog for provisioning the Oomph project itself.
 Projects can optionally {contain nested projects.
 It is stored in a <a href="DocSetupResource.html#ProjectResource" title="Chapter in Oomph Setup Documentation">project resource</a>.
 </p>

<a name="DocProject.nested">
Projects can optionally contain <a href="../../javadoc/org/eclipse/oomph/setup/ProjectContainer.html#getProjects()" title="Method in org.eclipse.oomph.setup.ProjectContainer"><code>nested</code></a> projects
 which may be stored in a separate <a href="DocSetupResource.html#ProjectResource" title="Chapter in Oomph Setup Documentation">project resource</a> or in the same resource as the <a href="../../javadoc/org/eclipse/oomph/setup/Project.html#getParentProject()" title="Method in org.eclipse.oomph.setup.Project"><code>containing</code></a> project.
 </p>

* Streams
  * == `org.eclipse.oomph.setup.Stream`
  * == container -- for -- tasks /
    * provision a workspace / THAT particular stream
    * install the tools -- needed to work with -- that particular stream
  * _Example:_ stream | Oomph project | Eclipse project catalog / provision the [source code of the Oomph project's master branch](https://github.com/eclipse-oomph/oomph) 

## Installations
* TODO:
<p>
 An <a href="../../javadoc/org/eclipse/oomph/setup/Installation.html" title="Interface in org.eclipse.oomph.setup"><code>installation</code></a> is a <a href="../../javadoc/org/eclipse/oomph/setup/SetupTaskContainer.html#getSetupTasks()" title="Method in org.eclipse.oomph.setup.SetupTaskContainer"><code>container</code></a> for the tasks to provision a specific installed Eclipse product.
 Its primary function,
 however,
 is that it specifies a reference to a product <a href="DocScope.html#DocVersion" title="Chapter in Oomph Setup Documentation">version</a>.
 The tasks <a href="DocTaskComposition.html" title="Article in Oomph Setup Documentation">gathered</a> for that product version are <a href="DocTaskExecution.html" title="Article in Oomph Setup Documentation">performed</a> to that installed product.
 The <a href="../user/wizard/DocInstallWizard.html" title="Article in Oomph Setup Documentation">installer wizard</a> creates an instance based on the selected product version on the <a href="../user/wizard/DocProductPage.html" title="Article in Oomph Setup Documentation">product page</a>.
 Even if the product installation hasn't been installed by Oomph,
 when Oomph is present,
 an installation instance is automatically created
 to refer to the synthetic <a href="DocScope.html#DocVersion.self" title="Section in Oomph Setup Documentation">self</a> product version.
 </p>

## Workspaces

* == `org.eclipse.oomph.setup.Workspace`
* == container -- for the -- tasks / provision a SPECIFIC Eclipse workspace
* 's goal
  * specify >=0 references -- to -- project's streams
    * tasks gathered / EACH project streams -- are -- performed / 
      * that workspace
      * installed product -- used to open -- that workspace

* [installer wizard](../user/wizard/DocInstallWizard.md)
  * creates an instance -- based on the -- selected project streams | [project page](../user/wizard/DocProjectPage.md)

## Users

* TODO:
* ==<p>
 A <a href="../../javadoc/org/eclipse/oomph/setup/User.html" title="Interface in org.eclipse.oomph.setup"><code>user</code></a> is a <a href="../../javadoc/org/eclipse/oomph/setup/SetupTaskContainer.html#getSetupTasks()" title="Method in org.eclipse.oomph.setup.SetupTaskContainer"><code>container</code></a> for the tasks to provision
 every installed Eclipse product and every opened workspace ever used by the end-user.
 It provides an opportunity to customize all aspects of a user's experience with all their Eclipse products and workspaces.
 It also provides support for the following:
 <ul>
 <li>
 Recording the <a href="../../javadoc/org/eclipse/oomph/setup/User.html#getAcceptedLicenses()" title="Method in org.eclipse.oomph.setup.User"><code>accepted license</code></a> of each of the tools automatically installed tools,
 i.e., once a license is accepted,
 it can be recorded to avoid any future prompts involving that particular license.
 </li>
 Recording the <a href="../../javadoc/org/eclipse/oomph/setup/User.html#getUnsignedPolicy()" title="Method in org.eclipse.oomph.setup.User"><code>policy</code></a> with regard to handling of unsigned content,
 i.e., once unsigned content is accepted,
 that acceptance can be recorded to avoid any future prompts involving unsigned content.
 </li>
 <li>
 Recording configuration options, so called <a href="../../javadoc/org/eclipse/oomph/setup/User.html#getAttributeRules()" title="Method in org.eclipse.oomph.setup.User"><code>attribute rules</code></a>,
 that tailor where and how repositories, workspaces, and installations are physically stored.
 </li>
 </ul>
