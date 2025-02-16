* goal
  * 👀setup process -- to orchestrate the -- automated process 👀

* Setup task
  * `org.eclipse.oomph.setupTask`
  * 👀== fine-grained unit of work | overall setup process 👀/
    * declarative
  * EACH task | [scope](DocScope.md)
* overall setup process's steps
  * [gather tasks -- from -- VARIOUS scopes](DocTaskComposition.html)
  * [perform those tasks](DocTaskExecution.html)
    * -- determined by the -- task's properties
* task's properties
  * `id`
    * OPTIONAL
    * if it exists -> MUST be UNIQUE | [resource](DocSetupResource.html)
  * `excluded triggers`
    * == triggers | task is NOT applicable 
    * bootstrap trigger -- is excluded by -- MOST task implementations
      * Reason: 🧠MUST execute | running product / task implementation + supporting infrastructure are installed 🧠
    * `permissible triggers`
      * hard-coded -- by the -- task implementations
      * task authors -- can -- exclude them
  * `disabled`
    * == whether or NOT the task -- should be -- gathered
  * `restrictions`
    * OPTIONAL
    * == set of [scopes](DocScope.html) | task applies
  * `predecessors` & `successors`
    * == tasks / MUST be performed | BEFORE or AFTER, the task -- is -- performed
  * intrinsic `priority`
    * -- determined by the -- task implementor
* operations / -- supported by -- EACH task
  * logic / 
    * determine whether the task is performed &
      * -- is influenced by the -- trigger 
    * perform the task
    * documented / task
  * task / can be overridden -- by -- OTHER tasks | [process of gathering tasks](DocTaskComposition.md)
    * == 2 tasks -> 1! combined task /
      * specific / EACH task implementation
      * documented / task
    * -- by yielding a -- override token
    * 2 tasks / yield SAME override tokens -> are merged | [initial task gathering phase](DocTaskComposition.md)
  * [task / can be consolidated](DocTaskComposition.md) | end of the task gathering phase
    * Reason: 🧠optimize its representation in preparation | BEFORE performing the task 🧠

# Trigger

* == `org.eclipse.oomph.setup.Trigger`
* types
  * Bootstrap
    * uses
      * | use [installer wizard](../user/wizard/DocInstallWizard.md)
  * Startup
    * uses
      * | product is first launched,
      * | automated task performance is enabled,
      * | there are tasks / need to be performed,
    * [execution wizard](../user/wizard/DocUpdateWizard.md) -- will be -- opened | [progressPage page](../user/wizard/DocConfirmationPage.md)
  * Manual
    * uses
      * | invoke the
        * [project wizard](../user/wizard/DocImportWizard.md) or
        * [execution wizard](../user/wizard/DocUpdateWizard.md)
* specified / EACH Task 
* valid triggers == triggers / [filtered](DocTaskComposition.md) | gather tasks

# Variable Task

# Compound Task

# P2 Task

# Targlet Task
