# Padrões arquiteturais
+ Define os componentes do sistema geral, as suas responsabilidades e interligações.
	- Tenta-se evitar módulos pouco claros (novelo de lã)
+ Cada uma tem diferentes vantagens e desvantagens
	- É necessário ter cuidado ao escolher, porque alterar após implementação é mais custoso do que alterar um padrão de desenho.
+ Também referidos como estilos arquiteturais (?).
+ Normalmente existe apenas um padrão dominante num sistema, ao contrário de padrões de desenho, que podem existir vários num sistema.
+ Classificação:
	- *Mud to structure* -  Decomposição controlada e.g. Layers, event-based
	- Sistemas interativos - Interação entre pessoa e PC e.g. MVC
	- Sistemas distribuídos - e.g. Client-server, P2P
	- Sistemas adaptáveis - e.g. Microkernel
	- Sistemas paralelos - e.g. Map & reduce

## Padrão de Layers
+ Níveis mais elevados são mais abstratos e níveis meis baixos são mais concretos
	- Promove a portabilidade
+ Sistemas não ideais: Layer bypass (duas camadas não adjacentes interagem), callbacks, vertical layers
	- Não quer dizer que não se possa usar, mas não é recomendável
+ Atributos de qualidades:
	- Modificabilidade - Fácil de mudar layers porque estão separadas
	- Portabilidade - Pode-se abstrair do ambiente e usar noutros
	- Reutibilidade - 
	- Problemas de desempenho - degradação devido a excesso de layers

## Padrão de Pipes e Filters
+ Processa-se streams de dados que são proessados em filtros
+ Filtros não partilham o estado com outros
+ Dados fluem de uma **fonte** para um ***sink***.
+ Sistemas não ideais: filter buffer size, application termination
+ Atributos de qualidade:
	- Reconfigurabilidade (modificabilidade) - É possível mudar/extender a configuração
	- Reutibilidade - É fácil fazer ciclos

## MVC (centrado no modelo)
+ Componente interagem com um modelo central
+ Existe um único modelo central que representa o estado
+ O modelo é apresentado ao utilizador atravé de um (ou mais) view(s)
+ Os controladores interagem com o modelo central
+ Os componentes view e controller são independentes um do outro mas todos estão dependentes do modelo central
+ Atributos de qualidade:
	- Modificabilidade - 
	- Extensibilidade - 
	- Concurrência - 

## Modelo Cliente-Servidor
+ Clientes pedem serviços a servidores
+ Apenas os clientes iniciam comunicação
+ Servidores não conhecem a identidade dos clientes até serem contactados
+ Atributos de qualidade:
	- Maintanibility - 

## Peer to Peer
+ Cada nodo pode agir como cliente e servidor
Atributos de qualidade:
	- Disponibilidade - Os dados ainda estão disponíveis se uma parte dele falhar
	- Escalabilidade - O sistema pode crescer sem alterar o código


# Implementação
+ Fazer com que o código reflita o desenho (Fazer com que os elementos arquiteturais sejam fáceis de localizar)
+ Code Templates
+ Frameworks

