Aplikacja działającą w środowisku rozproszonym, wykorzystująca do komunikacji gniazda TCP/IP. Jesto to mały system, w którego skład wchodzą podsystemy uruchamiane równolegle. 

System pełni rolę symulatora sklepu stacjonarnego, w którym klienci zamawiają towary za pośrednictwem terminali, zaś pracownicy sklepu zajmują się dostarczeniem i sprzedażą towarów według tych zamówień. 

Częściami tego systemu są podsystemy:
- Magazynier (Keeper) - obsługuje sklepowy magazyn z towarami. Odpowiada za rejestrację i wyrejestrowywanie klientów i pracowników oraz za obsługę ich żądań. Host i port Magazynu są powszechnie znane, wystawia na zadanym porcie interfejs.
- Dostawca (Deliverer) 
- Klient (Customer),
- Sprzedawca (Seller)

![shop-2](https://github.com/krisjt/basics-java/assets/132754764/049ea02f-f3f4-4ae8-a6c5-0e07acd06256)
