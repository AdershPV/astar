## A* Pathfinding Algorithm

To run this project in eclipse, you can use the 'import' option:

import > Git > Projects from Git > Clone URI

and paste this link https://github.com/adershpv/astar.git there.

### Program Short Description

A map is created by reading a text file (large_map.txt) from the resources folder. The characters '@' and 'X' is set as as the starting position and the ending postion respectively.
The shortest path from '@' to 'X' is generated using the A* algorithm.
The output result is shown in a graphical UI representation. Also a new text file (large_map_output.txt) is created inside the resources folder with the path shown using the character '#'

You can also change some of the default configurations by editing the PathFinderConfig.xml inside the resource folder without changing the code.
For eg. costOfMovement, tileImage
