Aplikacja o graficznym interfejsie użytkownika, pozwalającą na przeglądanie danych udostępnionych w Internecie poprzez otwarte API.

Wyświetlane na jej interfejsie dane pochodzą z portalu GIOŚ. 

Opis wystawionego API: https://powietrze.gios.gov.pl/pjp/content/api

Aplikacja pozwala na przeglądanie danych stacji pomiarowych oraz wybór stanowiska pomiarowego. Ponadto wizualizuje dane pomiarowe (wykres w osi czasu) oraz indeks jakości powietrza (kolory odpowiadające indeksowi).

Aplikacja jest modułowa (tj. powstała z wykorzystaniem JPMS (ang. Java Platform Module System)). 

Parsowanie danych odbywa się z wykorzystaniem biblioteki gson, służącej do przetwarzania danych w formacie JSON. 
