# USS Emafor System

## Objectives

The Objectives of this project are
* to convert a description of a system into a simulation model of
that system; 
* to implement that simulation in a shared memory concurrent programming language; 
* to use the implemented simulation to explore the behaviour of a complex system; 
* to gain a better understanding of safety and liveness issues in concurrent systems.

## The system to simulate

The _**USS Emafor**_ is a space station located near Ceres in the asteroid belt between Mars and Jupiter. The Emafor serves as a hub for the processing and distribution of ore mined from nearby asteroids. The raw ore is delivered by **cargo ships** that arrive at an **arrival wait zone**, approximately ten kilo- metres from the Emafor. At this point, a local **pilot** is transported to the cargo ship to safely handle the approach, docking, unloading of cargo, undocking and departure. 

Docking and undocking of **cargo ships** at the USS Emafor's **berth** require the assistance of several smaller spacecraft known as space tugs. The **berth** can only hold one cargo ship at a time. Before they commence docking a cargo ship, a **pilot** must have engaged three tugs. These tugs are used dur- ing the docking process, and released once unloading of cargo commences. That is, while cargo is being unloaded the tugs may be utilised by other ships. Before commencing undocking, a **pilot** must have en- gaged two tugs (undocking is a simpler procedure).

Following undocking, the cargo ship returns to a **departure wait zone**, and the tugs used for undock- ing are released. The **pilot** is then transported to the cargo ship, which can depart the region of the USS Emafor. The **pilot** can then acquire a new cargo ship (from the **arrival wait zone**). 

The USS Emafor employs a tug controller, whose job it is to coordinate requests from **pilots** and allo- cate the requested number of tugs once they are available. A **pilot** can therefore contact the controller once to acquire or release the services of _multiple_ tugs. 

One hazard associated with mining in the asteroid belt is the risk of collisions with space debris. The USS Emafor's **berth** is equipped with a **shield** which can be activated to protect the **berth** at times of high risk. While the shield is activated, no **cargo ships** can start docking or undocking from the **berth** until after the shield is deactivated. Ships that have already started the docking or undocking process when the shield is activated can safely complete their action. The shield is activated and deactivated by a shield operator.
