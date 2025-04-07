;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema4_btr_sum) (read-case-sensitive #t) (teachpacks ((lib "Teachpack_problema1.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Teachpack_problema1.ss" "installed-teachpacks")) #f)))
;date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri),
;restituisce la cifra BTR corrispondente (carattere) della rappresentazione della somma
(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

(define btr-sum ;val: stringa
  (lambda (u v)
    (let ((k (string-length u))
          
          )
        (normalized-btr (btr-carry-sum (normalized-btr u) (normalized-btr v) #\.))
        )
    ))

(define btr-carry-sum ;val: stringa date le rappresentazioni BTR di due interi (stringhe) e il riporto in entrata (carattere),
                       ;restituisce la rappresentazione BTR della somma inclusiva del riporto
  (lambda (u v c)
    (cond ((and (string=? u "") (string=? v ""))
           (string c))
          ((string=? u "")
           (string-append (btr-carry-sum "" (head v) (btr-carry #\. (lsd v) c)) (string (btr-digit-sum #\. (lsd v) c)))
           )
          ((string=? v "")
           (string-append (btr-carry-sum (head u) "" (btr-carry (lsd u) #\. c)) (string (btr-digit-sum (lsd u) #\. c)))
           )
          (else
           (string-append (btr-carry-sum (head u) (head v) (btr-carry (lsd u) (lsd v) c)) (string (btr-digit-sum (lsd u) (lsd v) c)))
           )
     )
    )
  )

(define normalized-btr ;val: stringa data una rappresentazione BTR (stringa), restituisce la rappresentazione non vuota
                       ;equivalente in cui le eventuali cifre zero (#\.) in testa, ininfluenti, sono rimosse
  (lambda (s)
    (cond ((= (string-length s) 0)
         "")
          ((and (= (string-length s) 1) (char=? (string-ref s 0) #\.))
           ".")
       ((char=? (string-ref s 0) #\.)
       (normalized-btr (substring s 1)))
       ((not(char=? (string-ref s 0) #\.))
        s)
       )
    ))

(define lsd ;val: stringa data una rappresentazione BTR (stringa), restituisce la cifra meno significativa
            ;(carattere) oppure zero (#\.) se l’argomento è la stringa vuota
  (lambda (s)
    (if (string=? s "")
        #\.
        (string-ref s (- (string-length s) 1))
     )
    ))

(define head ;val: stringa data una rappresentazione BTR (stringa), restituisce la parte che precede l’ultima
             ;cifra (stringa) oppure la stringa vuota ("") se l’argomento è la stringa vuota
  (lambda (s)
    (if (string=? s "")
        ""
        (substring s 0 (- (string-length s) 1))
     )
    ))

(define btr-carry ;val: stringa date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri),
                  ;restituisce il riporto BTR in uscita (carattere) conseguente alla somma delle cifre
  (lambda (u v c)
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                        ((char=? v #\+)  ; - + c
                         #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

     
;(btr-sum "-+--" "+") ;-+-.
;(btr-sum "-+--" "+")
;(btr-sum "--" "+")