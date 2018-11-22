# Design Patterns
+ São classificados de acordo com a sua utilidade
	++ Padrões de criação (cr)
		+++ Abstrai o processo de instanciação de objetos
			1. Factory method - Delega a escolha de criação da classe (Product, que se extende para ProductA e ProductB) a criar para outro objeto (para a classe Creator, que tem um método factoryMethod que retorna Product) e refere o novo objeto através de uma interface comum (IProduct)
			2. Abstract Factory - Dá uma interface para a criação de um conjunto de objetos (AbstractFactory); O objeto (Client) delega a criação de Product para uma instância concreta da factory (ConcreteFactory)
			3. Singleton - Existe apenas uma instância da classe no sistema (getInstance())
			4. Composite - Constrói objetos complexos recursivamente através da construção de objetos semelhantes
			5. Proxy - 
			6. Facade - Dá uma interface única para um conjunto de interfaces num sistema (O professor não recomenda - "diminuir o acoplamento")
			7. Adapter - Converte uma interface de uma classe noutra interface que o cliente está à espera
	++ Padrões de concurrência (co)	