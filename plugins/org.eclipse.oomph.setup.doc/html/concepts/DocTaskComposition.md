# Task Composition

* installation & provisioning process
  * | preparation for [task execution](DocTaskExecution.md), gather a [task](DocTask.md) list | AVAILABLE [scopes](DocScope.md)

## Scope List

* == ordered list of [scopes](DocScope.md)
* 👀| prepare a gathering task list, it's collected 👀
  * repeated / EACH [stream](DocScope.md) specified | [project page](../user/wizard/DocProjectPage.md) or [workspace](DocScope.md)
  * collect steps
    * visit the [product version](DocScope.md) -- as selected in the -- [product page](../user/wizard/DocProductPage.md) or in the [installation](DocScope.md)
      * add that product version's containing [products](DocScope.md)' containing [product catalog](DocScope.md) | list
      * add that product version's containing product | list
      * add that product version itself | list
    * Visit a <a href="DocScope.html#DocStream" title="Chapter in Oomph Setup Documentation">stream</a>
 as selected in the <a href="../user/wizard/DocProjectPage.html" title="Article in Oomph Setup Documentation">project page</a> or in the <a href="DocScope.html#DocWorkspace" title="Chapter in Oomph Setup Documentation">workspace</a>, if there is one.
      * Add that stream's containing <a href="DocScope.html#DocProject" title="Chapter in Oomph Setup Documentation">projects</a>'s containing <a href="DocScope.html#DocProjectCatalog" title="Chapter in Oomph Setup Documentation">project catalog</a> to the list.
 Add that stream's outer-most containing project to the list
 and proceed down through the nested projects up to the stream's directly containing project,
 adding each project to the list along the way.
 Add that stream itself to the list.
    * Add the <a href="DocScope.html#DocInstallation" title="Chapter in Oomph Setup Documentation">installation</a> to the list
    * Add the <a href="DocScope.html#DocWorkspace" title="Chapter in Oomph Setup Documentation">workspace</a> to the list.
    * Add the <a href="DocScope.html#DocUser" title="Chapter in Oomph Setup Documentation">user</a> to the list.

<a name="ScopeList.scopeVariables">
<p>
 For each list of scopes, an ordered list of tasks is collected.
 Initially, for each scope in the list,
 three <a href="DocTask.html#DocVariableTask" title="Chapter in Oomph Setup Documentation">variables</a> are induced,
 one each for the <a href="DocScope.html.name" title="Section in Oomph Setup Documentation">name</a>, <a href="DocScope.html.label" title="Section in Oomph Setup Documentation">label</a>, and <a href="DocScope.html.description" title="Section in Oomph Setup Documentation">description</a>
 attributes of the scope
 where the variable name is prefixed with the <a href="../../javadoc/org/eclipse/oomph/setup/ScopeType.html" title="Class in org.eclipse.oomph.setup"><code>scope type</code></a> as follows:
 <ul>
 <li>
 <code>scope.product.catalog</code>
 </li>
 <li>
  <code>scope.product</code>
 </li>
 <li>
  <code>scope.product.version</code>
 </li>
 <li>
 <code>scope.project.catalog</code>
 </li>
 <li>
 <code>scope.project</code>
 </li>
 <li>
 <code>scope.project.stream</code>
 </li>
 <li>
 <code>scope.installation</code>
 </li>
 <li>
 <code>scope.workspace</code>
 </li>
 <li>
  <code>scope.user</code>
 </li>
 </ul>
 The value of each such variable will be the value of that attribute for the scope.
 If the scope's label is <code>null</code>, the name is used as the label value
 and if the scope's description is <code>null</code>, the scope's label is used as the description value.
 In addition, to the <code>name</code> variable,
 for each product, product version, project, and project stream
 an additional variable with the name suffix <code>.qualifier</code> is induced
 where the value is the <a href="../../javadoc/org/eclipse/oomph/setup/Scope.html#getQualifiedName()" title="Method in org.eclipse.oomph.setup.Scope"><code>qualified name</code></a> of the scope.
 For example,
 the value of the <code>scope.project.stream.name.qualified</code> variable of the Oomph.setup project's master stream
 is <code>org.eclipse.oomph.master</code>
 All these induced variables are added, in scope order, to the initial gathered list of tasks.
 </p>

<a name="ScopeList.footer">
<p>
 Additional tasks are gathered into the task list from the ordered scopes by visiting each contained task of each scope as follows:
 <ul>
 <li>
 If the task is disabled,
 ignore the task.
 </li>
 <li>
 If the list of scopes doesn't contain all of the task's restrictions,
 ignore the task.
 </li>
 <li>
 If the task is <a href="DocTask.html#DocCompoundTask" title="Chapter in Oomph Setup Documentation">compound</a>,
 recursively visit each of that compound task's contained tasks.
 </li>
 <li>
 Otherwise,
 add the task to the list of tasks.
 In other works,
 the gathered task list will only contain leaf tasks.
 </li>
 </ul>
 At the end of scope processing,
 the gathered task list contains
 all of the per-stream tasks,
 or just task list if there is no stream.
 Each task list (or the one task list) is further processed in two phases.
 The initial phase is applied for each task list separately.
 The per-stream task lists are then concatenated into a single list and the second phase is applied to that final composed list,
 or the one task list is further processed by the second phase.
 The processing of the the task list in each phase is roughly same.
 </p>

## Task List
<p>
 The task list is processed to induce additional tasks,
 to <a href="DocTask.html.override" title="Section in Oomph Setup Documentation">override and merge</a> tasks,
 to evaluate and expand variables
 and to <a href="DocTask.html.order" title="Section in Oomph Setup Documentation">reorder</a> tasks.
 The members of the task list that are <a href="DocTask.html#DocVariableTask" title="Chapter in Oomph Setup Documentation">variables</a> induce an initial set of keys,
 i.e., a set of all variable names.
 Oomph tasks are modeled with EMF,
 so each task instance knows it corresponding EMF class.
 During the initial phase processing,
 the list of tasks is analyzed to determine the set of EMF classes that are required to implement all the tasks in the list.
 Each EMF class is processed as follows:
 <ul>
 <li>
 If the class contains enablement annotations,
 induce <a href="DocTask.html#DocP2Task" title="Chapter in Oomph Setup Documentation">p2 tasks</a>,
 i.e., tasks to install the necessary implementations in the installed product,
 and add these induced tasks to the head of the task list.
 </li>
 <li>
 If the class contains variable annotations,
 induce the <a href="DocTask.html#DocVariableTask" title="Chapter in Oomph Setup Documentation">variables</a>
 and add them to the head of the task list.
 </li>

 <li>
 Visit each attribute of the class as follows:
 <ul>
 If the attribute's type isn't String,
 ignore it.
 <li>
 If the attribute has both a variable annotation and a variable rule annotation,
 visit each instance of that attribute's class in the task list as follows:
 <ul>
 <li>
 If the attribute's value in that instance task is not empty,
 the rule isn't needed,
 so ignore it.
 </li>
 <li>
 If the instance task doesn't have an ID,
 or it does,
 but the key composed from the ID value, with '.' and the attribute name appended,
 is already in the list of induced keys,
 the rule isn't needed,
 so ignore it.
 </li>
 <li>
 Induce a rule variable for that attribute,
 if that rule variable hasn't already been induced for another task instance.
 </li>
 </ul>
 A mapping from rule variables to their inducing attribute is maintained.
 <li>
 </li>
 </li>
 </ul>
 </li>
 </ul>
 At the end of this processing for the first phase,
 the list of tasks includes additional induced variables and tasks.
 For the final phase,
 the composed list of tasks contains no variables, because they've all been evaluated and expanded,
 and the induced tasks are already present.
 </p>

<a name="TaskList.substitutions">
<p>
 Further processing proceeds as follows:
 <ul>
 <li>
 Build a substitution map,
 i.e., task-to-task map of substitutions by visiting each task as follows:
 <ul>
 <li>
 Put the task's <a href="DocTask.html.override" title="Section in Oomph Setup Documentation">override token</a> into an token-to-task map,
 and if another task for that token is already present in the token-to-task map,
 the visited task is a substitution for that other task so add that to the substitution map.
 As such,
 tasks later in the list can override tasks earlier in the list.
 </li>
 The substitution map is then further processed to follow the substitution mappings so as to represent the direct substitutions.
 </ul>
 </li>
 </ul>
 </p>

<h3><a name="InitialPhase"></a>2.1&nbsp;&nbsp;Initial Phase</h3>
<p>
 For the initial phase processing,
 all the tasks are efficiently copied,
 including the copying of the containing scopes.
 The copying process takes the task-to-task <a href="DocTaskComposition.html#TaskList.substitutions" title="Section in Oomph Setup Documentation">substitution</a> map into account,
 i.e., each task is logically replaced in the copy by its <a href="DocTask.html.override" title="Section in Oomph Setup Documentation">merged override</a>.
 As such,
 only the final overridden merged task remains in the resulting task list copy
 and all references to the overridden and overriding tasks will reference the final merged override instead.
 Further processing of the task list proceeds with this copied task list.
 </p>
 <p>
 An explicit key map,
 i.e., a map from variable name to variable,
 is computed by visiting each variable in the task list.
 Note that the preceding copying process will have eliminated duplicate variables.
 The initial phase processing then proceeds by visiting each task with a non-empty ID attribute as follows:
 <ul>
 <li>
 Visit each attribute of the task's EMF class
 with respect to the variable name induced by appending '.' and the attribute name to the value of the task's ID attribute:
 <ul>
 <li>
 If the attribute is the ID attribute of a task,
 or the attribute isn't a single-valued String-typed attribute,
 ignore it.
 </li>
 <li>
 If the explicit keys contains the induced variable's name,
 the task's value for the attribute is empty,
 and the attribute has a variable annotation,
 change the task's value for the attribute to be a reference to the explicit variable.
 </li>
 <li>
 If the explicit keys do not include the induced variable name,
 induce a new variable
 record that it's an explicit key,
 add it to the task list,
 and also do the following:
 <ul>
 <li>
 If the task's value for the attribute is empty,
 and the attribute has a variable annotation,
 change the task's value for the attribute to be a reference to the induced variable.
 </li>
 <li>
 Conversely,
 if the task's value for the attribute is non-empty,
 set the induced variable's value to that value,
 and as for the empty value case,
 if the attribute has a variable annotation,
 change the task's value for the attribute to be a reference to the induced variable.
 </li>
 <li>
 Also induce rule variables for each of the attribute's rule annotations,
 recording them as explicit keys.
 </li>
 <li>
 And finally,
 if the attribute has a variable annotation,
 and the induced variable's value is a self reference that would lead to circular evaluation,
 induce a yet another variable with the induced variable's name suffixed with <code>.explicit</code>
 from the explicit annotations of the attribute,
 and also change the self-referencing variable's value to refer to that explicit variable.
 </li>
 </ul>
 </li>
 </ul>
 </li>
 <li>
 Visit each task to handle its so called active annotations.
 Such annotations can be used on variables to compose a set of choices
 from choices defined in other variables,
 or to induce choices from the value of another variable.
 TODO examples
 </li>
 <li>
 Visit each variable task to build a key-to-value map.
 Analyze that map to determine the variable references,
 reordering the map base on this dependency analysis.
 And expand the variables in the ordered map.
 </li>
 <li>
 Expand the values of all attributes, of all tasks based on the key-to-value map, except those attributes marked as not expandable.
 E.g., the name attribute of a variable itself is not expanded.
 </li>
 <li>
 Recall that the gathering of tasks effectively <a href="DocTaskComposition.html#ScopeList.footer" title="Section in Oomph Setup Documentation">ignore</a> <a href="DocTask.html#DocCompoundTask" title="Chapter in Oomph Setup Documentation">compound tasks</a>.
 But those compound tasks can specify <a href="DocTask.html.order" title="Section in Oomph Setup Documentation">predecessors and successors</a> and well as <a href="DocTask.html.restrictions" title="Section in Oomph Setup Documentation">restrictions</a>.
 As such,
 those predecessors and successors are expanded to become references to the leaf tasks.
 Furthermore,
 the resulting expanded predecessors and successors,
 as well at the restrictions,
 are propagated down to the leaf tasks.
 </li>
 </ul>
 <p>

<h3><a name="FinalPhase"></a>2.2&nbsp;&nbsp;Final Phase</h3>
<p>
 The final phase processes a task list that is a concatenation of task lists produced from the initial phase,
 or just the one task list already processed by the initial phase.
 As such,
 it's working with task copies for which all variables have been expanded and eliminated.
 The processing for this phase augments the substitution map
 by analyzing the task list for structural duplicates.
 It then applies those substitutions,
 i.e., overriding and merging duplicate tasks,
 thereby reducing the task list before further processing.
 </p>

<h3><a name="Reorder"></a>2.3&nbsp;&nbsp;Reordering</h3>
<p>
 The processing of the task list,
 particularly task <a href="DocTask.html.override" title="Section in Oomph Setup Documentation">overriding</a> and merging,
 affects the overall order of the task list such that it's different from the original authored order gathered from the scopes.
 Not only that,
 when multiple streams are involved,
 <a href="DocTaskComposition.html#FinalPhase" title="Chapter in Oomph Setup Documentation">final phase</a> processing is dealing with a concatenated list in which the tasks must be properly reordered.
 To support that,
 each task has an intrinsic <a href="DocTask.html.order" title="Section in Oomph Setup Documentation">priority</a>;
 the task list is primarily sorted according to that priority.
 Each task also specifies <a href="DocTask.html.order" title="Section in Oomph Setup Documentation">predecessors and successors</a>;
 the task list is secondarily sorted to respect that induced partial order.
 After these two sorting steps,
 the tasks in the list are modified to clear both the predecessors and successors
 and then the predecessors are set to form a chain that induces an overall order that's exactly this final order of sorted task list;
 this chain excludes variables.
 This chain of dependencies ensures that the final phase processing,
 which deals with the concatenated task lists,
 will properly interleave the tasks (because of the priority sorting)
 while also respecting the per-stream order of the multiple streams.
 <p>

<h3><a name="Filter"></a>2.4&nbsp;&nbsp;Trigger Filtering</h3>
<p>
 Each task that <a href="DocTask.html.excludedTriggers" title="Section in Oomph Setup Documentation">excludes</a> the current <a href="DocTask.html#DocTrigger" title="Chapter in Oomph Setup Documentation">trigger</a> is removed from the task list.
 Note that the task list gathering process gathers <b>all</b> tasks
 because the task list is analyzed to determine which tasks need to be installed for all possible triggers.
 So for <a href="DocTask.html#DocTrigger.bootstrap" title="Section in Oomph Setup Documentation">bootstrap bootstrap </a>trigger,
 even the tasks that can't execute until they're running in an installed product are analyzed to ensure that,
 once the product is installed,
 the tasks that will need to perform in that installation,
 i.e., for <a href="DocTask.html#DocTrigger.startup" title="Section in Oomph Setup Documentation">startup</a> or <a href="DocTask.html#DocTrigger.manual" title="Section in Oomph Setup Documentation">manual</a> trigger,
 are properly installed.
 The processing of all tasks also implies that at bootstrap time,
 all  variables that will be needed in the running installed product will be <a href="../user/wizard/DocVariablePage.html" title="Article in Oomph Setup Documentation">prompted</a> early
 and hence will already be available in the running installed product.
 </p>

<h3><a name="Consolidation"></a>2.5&nbsp;&nbsp;Consolidation</h3>
<p>
 The final task list processing step
 removes all variables from the task list
 and <a href="DocTask.html.consolidate" title="Section in Oomph Setup Documentation">consolidates</a> each remaining task.
 At this point,
 the tasks in the list are ready to be <a href="DocTaskExecution.html" title="Article in Oomph Setup Documentation">performed</a>.
 </p>

<p align="right">
<a href="DocSetupResource.html" title="Backward to Setup Resources"><img src="../../images/backward.png" border="0"></a>&nbsp;<a href="DocTaskExecution.html" title="Forward to Task Execution"><img src="../../images/forward.png" border="0"></a></p>
<!-- <div class="help_breadcrumbs breadcrumbs_bottom"><a href="../Overview.html" title="Oomph Setup Documentation">Oomph Setup Documentation</a> > <a href="index.html" title="Category in Oomph Setup Documentation">Concepts</a></div> -->

<div class="copyright">Copyright (c) 2014 Eike Stepper (Loehne, Germany) and others.<br>All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse Public License v2.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v20.html</div>
</body>
</html>
