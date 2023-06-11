## Functional Requirements
1. save text and generate a short url
2. allow tagging of pastes, and search by tags 
3. set expiration time for paste urls 
4. support login for users. Implementation should also allow for anonymous users 
5. password protect paste (optionally)
6. Access Control for paste - limit paste to be visible only to select users 

7. allow for saving images as next steps 

----
## API Design

read(tinyUrl) => content
save(content) => tinyUrl
save(content, expiration_time, tags) => tinyUrl
(un)share(tinyUrl, userId) -> add users to the tinyUrl
deletePaste(tinyUrl) => void 

---
## Capacity Estimates:
Per hour - 100 Pastes 
1 Paste  - 1 KB 

1 Month => 100 * 24 * 30 => 72000 KB => 72 GB

Tags, expriation time, acls... 

Write:Read => 1:10
---

## High Level Architecture
## todo features
integration testing - karate / jmeter
kibana
prometheus + graphana for monitoring - k8s cluster health
resiliency - retry pattern (resilience4j)
circuit breaker
istio
deploy in prod cluster and dev cluster
deploy in gcp

## baby steps
hello world springboot
basic k8s setup 
convert that to helm
introduce postgres db



