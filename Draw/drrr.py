import matplotlib.pyplot as plt

plt.figure()
plt.grid(True)

plt.plot(  [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
           [11200,11200, 14400, 10900 , 10599 , 10700 , 12300 , 10600 ,12201 , 10700 , 12001 , 10800 , 16100 , 11100, 13499,
            15700 , 15600 , 11801 , 16600 , 14200 , 16500 ],
           color='lightblue', linewidth=3, marker='.', markersize = 8, label="Eratosthenes alg1" , markeredgecolor = 'red')
plt.plot(  [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
           [10799,9100 , 9501 , 12700 , 9199 , 9999 , 12300 , 14200 , 7001 , 11101 , 7701 , 9601 , 9401 , 7701 , 7700,
            18900 , 12600 , 9501 , 14300 , 14800 , 15000 ],
           color='red', linewidth=3, marker='.', markersize = 8, label="Eratosthenes alg2", markeredgecolor = 'blue' )
plt.plot(  [10, 15,20,25, 30,	35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
           [9700 , 8100 , 7799 , 25100 , 25300 , 8800 , 39100 ,11800 , 18800 , 10301 , 18401 , 10500 , 17300 , 18600 ,19100,
            25400 , 15400 , 12701 , 15901 ,14000 , 16900 ],
           color='yellow', linewidth=3, marker='.', markersize=8, label="Eratosthenes alg3", markeredgecolor = 'magenta' )

plt.plot( [10, 15,20,25, 30,35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
           [92500 , 42400 , 40101 , 39500 , 38200 , 43300 ,40699 , 39701 , 45000 , 38999, 53500 , 44600 , 43900 , 42699 , 46900,
             122299 , 97800 , 100400  ,92600 , 102899 , 93600],
           color='green', linewidth=3, marker='.', markersize = 8, label="Eratosthenes alg4", markeredgecolor = 'black' )

plt.plot( [10, 15,20,25, 30,35, 40, 45,	50, 55,	60, 65,70,75,80, 100, 120, 140, 160, 180, 200],
           [13000,11301 , 12699 , 12400 , 17800 , 11100 , 10700 , 10899 ,21899, 15800 , 11800 , 17000 ,23601 , 11401, 21300,
            14089, 16700, 16870, 10899, 12877, 15999],
           color='black', linewidth=3, marker='.', markersize = 8, label="Eratosthenes alg5", markeredgecolor = 'green' )



plt.axis([ 0,250, 0, 125000])
plt.xlabel('The Nth term')
plt.ylabel('Nanoseconds')
plt.title('Algorithms comparison')
plt.legend()
plt.savefig('Comparing.png', dpi = 300)

plt.show()