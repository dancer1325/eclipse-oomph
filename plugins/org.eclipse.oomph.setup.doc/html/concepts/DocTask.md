* goal
  * 👀setup process -- to orchestrate the -- automated process 👀

* Setup task
  * `org.eclipse.oomph.setupTask`
  * 👀== fine-grained unit of work | overall setup process 👀/
    * declarative
  * EACH task | [scope](DocScope.html)
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
  * TODO:Logic for determining whether the task <a href="../../javadoc/org/eclipse/oomph/setup/SetupTask.html#isNeeded(org.eclipse.oomph.setup.SetupTaskContext)" title="Method in org.eclipse.oomph.setup.SetupTask"><code>needs</code></a> to perform
 as well the logic for <a href="../../javadoc/org/eclipse/oomph/setup/SetupTask.html#perform(org.eclipse.oomph.setup.SetupTaskContext)" title="Method in org.eclipse.oomph.setup.SetupTask"><code>performing</code></a> the task.
 The determination of whether that the task needs to perform
 is influenced by the <a href="DocTask.html#DocTrigger" title="Chapter in Oomph Setup Documentation">trigger</a>,
 i.e.,
 it affects how detailed will be the analysis for determining whether the task needs to be performed
 with the general goal being to <a href="DocTaskExecution.html" title="Article in Oomph Setup Documentation">perform</a> only those tasks strictly needed for the <a href="DocTask.html#DocTrigger.startup" title="Section in Oomph Setup Documentation">startup</a> trigger.
 This behavior is documented on a per-task basis.

  * task, in general, can be <a href="../../javadoc/org/eclipse/oomph/setup/SetupTask.html#overrideFor(org.eclipse.oomph.setup.SetupTask)" title="Method in org.eclipse.oomph.setup.SetupTask"><code>overridden</code></a> by other tasks during the process of <a href="DocTaskComposition.html" title="Article in Oomph Setup Documentation">gathering</a> tasks.
 Which tasks are overridden by which other tasks,
 and how that override merges two tasks into a single combined task
 is specific to each task implementation and is therefore documented on a per-task basis.
 A task implement supports overrides by yielding a so called <a href="../../javadoc/org/eclipse/oomph/setup/SetupTask.html#getOverrideToken()" title="Method in org.eclipse.oomph.setup.SetupTask"><code>override token</code></a>.
 Two tasks that yield equal override tokens are merged during the <a href="DocTaskComposition.html#InitialPhase" title="Chapter in Oomph Setup Documentation">initial</a> task gathering phase.
 </li>

  * task / can be <a href="../../javadoc/org/eclipse/oomph/setup/SetupTask.html#consolidate()" title="Method in org.eclipse.oomph.setup.SetupTask"><code>consolidated</code></a>.
 At the end of the task gathering phase,
 each task is <a href="DocTaskComposition.html#Consolidation" title="Chapter in Oomph Setup Documentation">consolidated</a> to optimize its representation in preparation before the task is <a href="DocTaskExecution.html" title="Article in Oomph Setup Documentation">performed</a>.
 </li>

<a name="DocTask.footer">
</ul>
 </p>

<h2><a name="DocTrigger"></a>1&nbsp;&nbsp;Trigger</h2>


<a name="DocTrigger.introduction">
<p>
 There are three types of <a href="../../javadoc/org/eclipse/oomph/setup/Trigger.html" title="Class in org.eclipse.oomph.setup"><code>triggers</code></a>:
 <ul>

<a name="DocTrigger.bootstrap">
<li>
 <a href="../../javadoc/org/eclipse/oomph/setup/Trigger.html#BOOTSTRAP" title="Field in org.eclipse.oomph.setup.Trigger"><code>Bootstrap</code></a> applies when using the <a href="../user/wizard/DocInstallWizard.html" title="Article in Oomph Setup Documentation">installer wizard</a>.
 </li>

<a name="DocTrigger.startup">
<li>
 <a href="../../javadoc/org/eclipse/oomph/setup/Trigger.html#STARTUP" title="Field in org.eclipse.oomph.setup.Trigger"><code>Startup</code></a> applies when a product is first launched, automated task performance is enabled, and there are tasks that need to be performed,
 at which point the <a href="../user/wizard/DocUpdateWizard.html" title="Article in Oomph Setup Documentation">execution wizard</a> will be opened on the <a href="../user/wizard/DocConfirmationPage.html" title="Article in Oomph Setup Documentation">progressPage page</a>.
 </li>

<a name="DocTrigger.manual">
<li>
 <a href="../../javadoc/org/eclipse/oomph/setup/Trigger.html#MANUAL" title="Field in org.eclipse.oomph.setup.Trigger"><code>Manual</code></a> applies when invoking the <a href="../user/wizard/DocImportWizard.html" title="Article in Oomph Setup Documentation">project wizard</a> or directly invoking <a href="../user/wizard/DocUpdateWizard.html" title="Article in Oomph Setup Documentation">execution wizard</a>.
 </li>

<a name="DocTrigger.summary">
</ul>
 <p>
 Each task specifies its <a href="DocTask.html.excludedTriggers" title="Section in Oomph Setup Documentation">valid</a> triggers
 determining whether that task is <a href="DocTaskComposition.html#Filter" title="Chapter in Oomph Setup Documentation">filtered</a> when tasks are gathered.
 </p>

<h2><a name="DocVariableTask"></a>2&nbsp;&nbsp;Variable Task</h2>


<h2><a name="DocCompoundTask"></a>3&nbsp;&nbsp;Compound Task</h2>


<h2><a name="DocP2Task"></a>4&nbsp;&nbsp;P2 Task</h2>


<h2><a name="DocTargletTask"></a>5&nbsp;&nbsp;Targlet Task</h2>

