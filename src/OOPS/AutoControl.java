package OOPS;

import Geometrics.*;

import java.util.ArrayList;
import java.util.Random;
import OOPS.SnakeBodyPart.Orientation;

/**
 * Class that defines the automatic control of the game.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 * @version v1.0    13/04/2024
 *
 * 'https://www.geeksforgeeks.org/a-search-algorithm/'
 */
public class AutoControl implements Control
{

    //TODO: IMPLEMENTAR O AUTOCONTROL (Algorithm A*)
    private Node[][] arena;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> pathList = new ArrayList<>();
    private Node startNode, goalNode, currentNode;
    private boolean goalReached = false;
    private int step = 0;

//------------- GAME SETTINGS    --------------------------------------------------------------------------------------------------------

    private Game g;
    private final int col,row, cellLength;
    static final int RIGHT = 0,DOWN = 1,LEFT = 2,UP = 3;


    /**
     * Constructor of the AutoControl
     * @param g Game
     */
    public AutoControl(Game g)
    {
        this.g = g;
        this.col = g.n();
        this.row = g.m();
        cellLength = g.cellLength();
        instantiateNodes(); // creates the nodes(size of matrix)
                            // the same with the grid class
    }

    /**
     * Computes the next step of the player
     */
    @Override
    public int handle()
    {
        Ponto fruit = ((FiguraGeometrica) g.f()).findCentroid();
            int goalCol = (int)fruit.x()/cellLength;;
            int goalRow = (this.row - (int)fruit.y()/cellLength)-1; //inverte a linha porque usamos um plano cartesiano ao contrario;
        return this.findThePath(goalCol, goalRow);
    }
//------ ALGORTIHM A* ---------------------------------------------------------------------

    /**
     * Method that instantiates the nodes of the arena
     */
    public void instantiateNodes()
    {
        arena = new Node[this.row][this.col];

        int col = 0;
        int row = 0;
        while(col < this.col && row < this.row)
        {
            arena[row][col] = new Node(row, col);
            col++;
            if(col == this.col)
            {
                col = 0;
                row++;
            }
        }

    }


    /**
     * Method that sets the start, goal nodes and obstacles nodes
     * @param startCol Column of the start node
     * @param startRow Row of the start node
     * @param goalCol Column of the goal node
     * @param goalRow Row of the goal node
     */
    public void setNodes(int startCol, int startRow,int goalCol, int goalRow)
    {
        resetArena();

        //set Start and Goal node
        startNode = arena[startRow][startCol];
        currentNode = startNode;
        currentNode.obstacle = true;
        findBehindNode(startNode);

        setObstacles();

        goalNode = arena[goalRow][goalCol];
        openList.add(currentNode); // o primer elemento onde começa a busca

        int col = 0;
        int row = 0;
        while(col < this.col && row < this.row)
        {

            getCost(arena[row][col]);
            col++;
            if(col == this.col)
            {
                col = 0;
                row++;
            }
        }
        
    }

    /**
     * Method that resets the arena
     */
    public void resetArena()
    {
        int col = 0;
        int row = 0;

        while (col < this.col && row < this.row)
        {
            arena[row][col].open = false;
            arena[row][col].checked = false;
            arena[row][col].obstacle = false;

            col++;
            if(col == this.col)
            {
                col = 0;
                row++;
            }
        }
        // RESET OTHER SETTINGS
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    /**
     * Method that sets the obstacles nodes
     */
    private void setObstacles()
    {

        //---- BODY SNAKE   ----------------------------------------------
        ArrayList<SnakeBodyPart> obstacles = g.s().body();
        for(SnakeBodyPart sbp : obstacles)
        {
            Ponto current = sbp.q().topLeftPoint();
            int col = (int)current.x()/cellLength;
            int row;
            if(current.y() % cellLength == 0)
                row = this.row - (int)current.y()/cellLength;
            else
                row = this.row - (int)current.y()/cellLength-1;// problema por usar el punto de la izquierda

            arena[row][col].obstacle = true;
        }
        //---------------------------------------------------------------
        //---------- OBSTACLES ------------------------------------------

        Obstacle[] o = g.obstacles();
        for (Obstacle obs : o)
            findObstacles(obs);



    }

    /**
     * Method that transfrom the obstacles (cartesian coordinates) into indexes of the matrix
     * @param o Obstacle to find
     */
    private void findObstacles(Obstacle o)
    {
            Obstacle temp = new Obstacle(o.p(), o.p().findCentroid(), o.angle());
            SegmentoReta[] segments = Poligono.createSegmentosReta(temp.p().pontos());
            double deltaX, deltaY;
            double magnitude;
            double normalizedX, normalizedY;

            for (SegmentoReta segment : segments)
            {
                // Calcular la dirección del segmento de recta
                 deltaX = segment.b().x() - segment.a().x();
                 deltaY = segment.b().y() - segment.a().y();
                 magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    normalizedX = deltaX / magnitude;
                    normalizedY = deltaY / magnitude;

                // Iterar a lo largo del segmento de recta
                for (double distance = -1; distance-1 < magnitude; distance += cellLength)
                {
                    int col = (int) ((segment.a().x() + normalizedX * distance) / cellLength);
                    int row = this.row - (int) ((segment.a().y() + normalizedY * distance) / cellLength) - 1;

                    // Verificar límites de la matriz
                    if (row >= 0 && row < this.row && col >= 0 && col < this.col)
                    {
                        arena[row][col].obstacle = true;
                    }
                }
            }



    }


    /**
     * Method that calculates the cost of a node
     * @param node Node to calculate the cost
     */
    public void getCost(Node node)
    {
        //GET G COST (the distance from the start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //GET H COST (the distance from the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //GET F COST
        node.fCost = node.gCost + node.hCost;
    }

    /**
     * Method that searches for the best path to the goal
     * @return true if the goal is reached, false if not
     */
    public boolean search()
    {
        while(!goalReached && step < 300)
        {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if (row - 1 >= 0)
                openNode(arena[row-1][col]);
            // OPEN THE LEFT NODE
            if (col - 1 >= 0)
                openNode(arena[row][col-1]);
            // OPEN THE DOWN NODE
            if (row + 1 < this.row)
                openNode(arena[row + 1][col]);
            // OPEN THE RIGHT NODE
            if (col + 1 < this.col)
                openNode(arena[row][col + 1]);

            //FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 99999;

            for (int i = 0; i < openList.size(); i++)
            {
                //check if this node's F cost is better
                if(openList.get(i).fCost < bestNodefCost && !openList.get(i).obstacle)
                {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if F cost is equal, check the G cost
                    else if(openList.get(i).fCost == bestNodefCost)
                        if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost)
                            bestNodeIndex = i;


            }
            // if there is no node in the openList, end the loop
            if(openList.isEmpty())
                break;

            // after the loop, openList[bestNodeIndex] is the next step (currentNode)
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode)
            {
                goalReached = true;
                trackThePath();
            }

            step++;

        }
        return goalReached;
    }

    /**
     * Method that opens a node and checks if it has been checked or opened before
     * @param node Node to be opened
     */
    public void openNode(Node node)
    {
        // me ajuda a abrir o nó e saber se ja passei por ele
        if(!node.open && !node.checked && !node.checked )
        {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Method that tracks the path from the goal to the start
     */
    public void trackThePath()
    {
        Node current = goalNode;
        while(current != startNode)
        {
            // como é uma lista sempre adicionarmos no inicio para ter a ordem certo do caminho
            findNeighborAndSetNextMove(current);
            pathList.addFirst(current);
            current = current.parent;
            this.currentNode = current;
        }

        goalNode.nextMove =-1;
    }


    /**
     * Method that finds the neighbour of a node and set the next move
     * @param node Node to find the neighbour
     */
    private void findNeighborAndSetNextMove(Node node)
    {
        Node neighbour = node.parent;
        if(neighbour == null)
            return;

        if( node.row+1 == neighbour.row &&node.col == neighbour.col )
            neighbour.nextMove = UP;


        if( node.row == neighbour.row && node.col-1 == neighbour.col )
            neighbour.nextMove = RIGHT;


        if( node.row-1 == neighbour.row && node.col == neighbour.col )
            neighbour.nextMove = DOWN;

        if( node.row == neighbour.row && node.col+1 == neighbour.col)
            neighbour.nextMove = LEFT;
    }

    /**
     * Method that finds the neighbours of a nodeHeadSnake
     * and set the behind node as an obstacle.
     * @param node Node to find the neighbours
     * @return Array of neighbours
     */
    private void findBehindNode(Node node)
    {
        int col = node.col;
        int row = node.row;
        Orientation o = new SnakeBodyPart.Orientation(g.s().head().o().i());
        o.invert(); // inverte a orientação
        int inverseOrientation = o.i();

        if(inverseOrientation == UP && row - 1 >= 0)
            arena[row-1][col].obstacle = true;

        if( inverseOrientation == LEFT && col - 1 >= 0)
            arena[row][col-1].obstacle = true;

        if(inverseOrientation == DOWN && row + 1 < this.row)
            arena[row + 1][col].obstacle = true;

        if(inverseOrientation == RIGHT && col + 1 < this.col)
            arena[row][col + 1].obstacle = true;


    }

//------------ METODOS PRINCIPAIS PARA GAME -----------------------------------------------------------------------------------------------------------------------------


    /**
     * Method that finds and put the snake to the path
     * @param goalCol Column of the goal node
     * @param goalRow Row of the goal node
     * @return 0 if the snake is on the path, 1 if the snake has to turn right, 2 if the snake has to turn left
     */
    private int findThePath(int goalCol, int goalRow)
    {
        Ponto current = this.g.s().head().q().topLeftPoint();
            int startCol = (int) current.x() / cellLength;
            int startRow = this.row - (int) current.y() / cellLength;

        this.setNodes(startCol, startRow, goalCol, goalRow);

        Node neighbor = currentNode;
            int nextOrientation =  neighbor.nextMove;
            int snakeOrientation = g.s().head().o().i();

        // search the best path
            this.search();

            if (pathList.isEmpty())
            {
                // primeira tentativa de nao morrer
                Random r = new Random();
                int randomRow = r.nextInt(0,this.row);
                int randomCol = r.nextInt(0,this.col);

                        //segunda tentativa
                        if(arena[randomRow][randomCol].obstacle)
                        {
                             randomRow = r.nextInt(0,this.row);
                             randomCol = r.nextInt(0,this.col);
                        }
                            // terceira tentativa e ultima, se não encontrar um lugar sem obstaculo podes morrer
                            if(arena[randomRow][randomCol].obstacle)
                            {
                                randomRow = r.nextInt(0,this.row);
                                randomCol = r.nextInt(0,this.col);
                            }


                        setNodes(startCol, startRow, randomCol, randomRow);
                        this.search();
            }

            if(startNode.nextMove == -1)
                findNeighborAndSetNextMove(startNode);


            // take the snake and put on the path
            if(snakeOrientation == startNode.nextMove )
            {
                return 0;
            }
            else if (snakeOrientation != startNode.nextMove)
            {
                nextOrientation = startNode.nextMove;
            }
            else
                nextOrientation = pathList.getFirst().nextMove;


            // the snake is on the path and the next move is different from the snake orientation
            if (nextOrientation == UP && snakeOrientation != UP)
            {
                //TODO: System.out.println("UP");
                return (g.s().head().o().i() == LEFT) ? 2 : 1;
            }
            else if (nextOrientation == DOWN && snakeOrientation != DOWN)
            {
                //TODO: System.out.println("DOWN");
                return (g.s().head().o().i() == LEFT) ? 1 : 2;
            }
            else if ( nextOrientation == RIGHT && snakeOrientation != RIGHT)
            {
                //TODO: System.out.println("RIGHT");
                return (g.s().head().o().i() == UP) ? 2 : 1;
            }
            else if (nextOrientation == LEFT && snakeOrientation != LEFT)
            {
                //TODO: System.out.println("LEFT");
                return (g.s().head().o().i() == UP) ? 1 : 2;
            }

            // the snake is on the path and the next move is the same as the snake orientation
            return 0;
    }

    /**
     * Method that prints the arena
     */
     public void print()
    {
        int col = 0;
        int row = 0;
        while(col < this.col && row < this.row)
        {
            if (col == goalNode.col && row == goalNode.row)
                System.out.print("| <G> |");
            else if (col == startNode.col && row == startNode.row)
                System.out.print("| <S> |");
            else if (arena[row][col].obstacle)
                System.out.print("||||||");
            else
                System.out.print("| " + row + "," + col + " |");
            col++;
            if (col == this.col)
            {
                System.out.println();
                col = 0;
                row++;
            }
        }
    }


//---------- subclass for the AutoControl (autoControl) -------------------------------------------------------------------------------------
    public class Node
    {
        Node parent;
        public int nextMove;
        public int col;
        public int row;
        int gCost;
        int hCost;
        int fCost;
        boolean obstacle;
        boolean open;
        boolean checked;

        public Node(int row, int col)
        {
            this.col = col;
            this.row = row;
            this.nextMove = -1;
        }

    }

}
