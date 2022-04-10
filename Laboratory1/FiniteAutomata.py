
#graphviz provides a simple pure-Python interface for the Graphviz graph-drawing software
import graphviz

g = graphviz.Digraph('finite_state_machine', filename='FA.gv')
g.attr(rankdir='LR', size='8,5')
g.node('Start', shape='plaintext')
g.edge("Start", "q0")

print("Enter production rules, when ready type \"Exit\" ")
verticesMap = {}


while True:
    value = input()
    if value == "exit" or value == "Exit" or value == "EXIT":
        break
    else:
        if len(value) == 4:
            if value[0] not in verticesMap.keys():
                verticesMap[value[0]] = "q" + str(len(verticesMap))

            if value[3] not in verticesMap.keys():
                verticesMap[value[3]] = "q" + str(len(verticesMap))

            g.attr('node', shape='circle')
            g.edge(verticesMap.get(value[0]), verticesMap.get(value[3]), label=value[2])

        else:
            if value[0] not in verticesMap.keys():
                verticesMap[value[0]] = "q" + str(len(verticesMap))
            if value[2] not in verticesMap.keys():
                verticesMap[value[2]] = "q" + str(len(verticesMap))
            g.attr('node', shape='doublecircle')
            g.node(verticesMap.get(value[2]))
            g.edge(verticesMap.get(value[0]), verticesMap.get(value[2]), label=value[2])

g.view()