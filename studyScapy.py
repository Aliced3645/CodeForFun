import threading
from scapy.all import *

#two things to test:
#1 listen the eth0 or scapy sniffing
#2 test the container in python

l = set()
def sendingthread():
    while 1:
        packet = IP(dst='127.0.0.1')/TCP(dport=8080)
        send(packet)
    
def heySniff(x):
    #x.show()
    global l
    l.add(x[IP].src)
    print l 

if __name__ == "__main__":
    print "Study Scapy"
    t = threading.Thread(target=sendingthread)
    t.daemon=True

    #t.start()
    #p = sr(IP(dst='127.0.0.1')/TCP(dport=8080))

    x = sniff(filter="ip", iface="eth0", prn=heySniff)
    x.show()
