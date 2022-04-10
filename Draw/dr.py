import matplotlib.pyplot as plt


fibNumberMeth1 =  [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200]
quick = [11200,11200, 14400, 10900 , 10599 , 10700 , 12300 , 10600 ,12201 , 10700 , 12001 , 10800 , 16100 , 11100, 13499,
            15700 , 15600 , 11801 , 16600 , 14200 , 16500 ]


fibNumberMeth2 = [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200]
merge = [10799,9100 , 9501 , 12700 , 9199 , 9999 , 12300 , 14200 , 7001 , 11101 , 7701 , 9601 , 9401 , 7701 , 7700,
            18900 , 12600 , 9501 , 14300 , 14800 , 15000 ]

fibNumberMeth3 = [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200]
heap =  [9700 , 8100 , 7799 , 25100 , 25300 , 8800 , 39100 ,11800 , 18800 , 10301 , 18401 , 10500 , 17300 , 18600 ,19100,
            25400 , 15400 , 12701 , 15901 ,14000 , 16900 ]

fibNumberMeth4 = [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200]
heaps = [92500 , 42400 , 40101 , 39500 , 38200 , 43300 ,40699 , 39701 , 45000 , 38999, 53500 , 44600 , 43900 , 42699 , 46900,
             122299 , 97800 , 100400  ,92600 , 102899 , 93600]

fibNumberMeth5 = [10, 15,20,25, 30,35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
heapsuk =          [13000,11301 , 12699 , 12400 , 17800 , 11100 , 10700 , 10899 ,21899, 15800 , 11800 , 17000 ,23601 , 11401, 21300,
            14089, 16700, 16870, 10899, 12877, 15999]


# plt.plot(fibNumberMeth1, timeMeth1, label = 'Execution time for nTH term', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'red')
plt.plot(fibNumberMeth1, quick, label='Eratosthenes alg1', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'blue')
plt.plot(fibNumberMeth2, merge, label='Eratosthenes alg2', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'red')
plt.plot(fibNumberMeth3, heap, label='Eratosthenes alg3', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'green')
plt.plot(fibNumberMeth4, heaps, label='Eratosthenes alg4', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'black')
plt.plot(fibNumberMeth4, heapsuk, label='Eratosthenes alg5', linewidth= 2, marker='.', markersize = 8, markeredgecolor = 'yellow')

plt.xticks([10, 20, 30, 40,	50,	60,70,80, 100, 120, 140, 160, 180, 200])


plt.title('Eratosthenes Algorithms')
plt.xlabel('N-elements')
plt.ylabel('Nanoseconds')
plt.legend()
plt.savefig('Comparing.png', dpi = 300)
plt.show()





