nodesList = []
adjList = {}
edges = []
mapToNodes = {}


# Adds to the node list //S bA  S = node, A = node2
def addToNodesList(node, node2):
    if node not in nodesList:
        nodesList.append(node)
    if node2 not in nodesList:
        nodesList.append(node2)
    return


# Creeates the edge
def add_edge(node1, weight, node2):  # S b     C
    if node2 == "":
        node2 = "finalNode"

    temp = []
    addToNodesList(node1, node2)
    if node1 not in adjList:  # If node not in the list
        temp.append([node2, weight])
        adjList[node1] = temp
    else:
        temp.extend(adjList[node1])  # adds all the elements of list to the end of the list.
        temp.append([node2, weight])
        adjList[node1] = temp
    return


finiteAutomation = {}
def createFiniteAutomation():
    for node in nodesList:
        finiteAutomation[node] = "q" + str(nodesList.index(node))


def printGraph():
    for node, array in adjList.items():
        print(str(finiteAutomation.get(node)), " ---> ", end='')
        for tup in array:
            print(finiteAutomation.get(tup[0]), " (", tup[1], ") ", end='')
        print()
    return


while True:
    userInput = input() #S aB
    node1 = userInput[0] #S
    weight = userInput[2]  #a
    if len(userInput) == 3:
        node2 = ""
    else:
        node2 = userInput[3]

    if userInput == "exit":
        break
    else:
        add_edge(node1, weight, node2)

createFiniteAutomation()
printGraph()
