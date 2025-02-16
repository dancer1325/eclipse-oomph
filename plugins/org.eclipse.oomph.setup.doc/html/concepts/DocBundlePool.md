# Bundle Pools

* allows
  * | install many products & provision many target platforms, 
    * highly disk-space efficient
      * Reason: 🧠ALL share a COMMON bundle pool 🧠
        * -> 1! .jar | disk / EACH installable unit
    * improve performance
      * Reason: 🧠if an installable unit is | pool -> NEVER again needs to be downloaded 🧠
* uses
  * by Oomph's
    * `DocP2Task`
    * `DocTargletTask`
  * by ALL Eclipse's p2 technology 

* Oomph's characteristics / improve behavior & performance
  * Bundle pools
  * technology layer | p2 (TODO: [which one does it refer?](https://github.com/orgs/eclipse-oomph/discussions/137))
