import matplotlib.pyplot as plt

plt.figure()
plt.grid(True)
plt.plot(  [3, 5, 10, 20,	30,	40,	50,	60,	80,	100, 150],
           [14.245,	14.348,	17.875,	13.643,	16.097,	15.827,	14.1078, 19.516, 22.347, 19.0675, 21.143],
           color='lightblue', linewidth=3, marker='.', markersize = 8, label="QuickSort" , markeredgecolor = 'red')
plt.plot(  [3, 5, 10, 20,	30,	40,	50,	60,	80,	100, 150],
           [0.0152,	0.0132,	0.593,	1.238,	1.166,	0.047,	0.8703,	0.665,	1.645,	1.985,	2.0692],
           color='red', linewidth=3, marker='.', markersize = 8, label="MergeSort", markeredgecolor = 'blue' )

plt.plot(  [3, 5, 10, 20,	30,	40,	50,	60,	80,	100, 150],
           [16.956, 18.905, 21.765, 16.208, 13.386, 14.754, 16.7825, 18.1395, 17.645, 16.911, 19.092],
           color='yellow', linewidth=3, marker='.', markersize = 8, label="HeapSort", markeredgecolor = 'magenta' )

plt.plot(  [3, 5, 10, 20,	30,	40,	50,	60,	80,	100, 150],
           [13.976,	21.375,	15.105,	16.294,	19.376,	21.740,	21.1025,	17.075,	19.721,	18.312,	20.6422],
           color='green', linewidth=3, marker='.', markersize = 8, label="BubbleSort", markeredgecolor = 'black' )


plt.axis([ 0, 160,0,25])
plt.xlabel('The Nth term')
plt.ylabel('Milliseconds')
plt.title('Algorithms comparison')
plt.legend()

plt.show()

