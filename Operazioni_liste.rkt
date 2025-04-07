;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Operazioni_liste) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define lista-ref ;val: elemento in posizione k
  (lambda (ls k) ;ls: lista k: intero
    (if (= k 0)
        (car ls)
        (lista-ref (cdr ls) (- k 1))
        )
    ))

(define giustapposizione ;val: lista
  (lambda (ls1 ls2)
    (cond ((null? ls1)
           ls2)
          ;((null? ls2)
          ;ls1)
          (else
           (cons (car ls1) (giustapposizione (cdr ls1) ls2))
           )
          )
    ))

(define rovescio-x ;val: lista
  (lambda (ls) ;ls: lista
    (if (or (null? ls) (null? (cdr ls)))
        ls
        (append (rovescio-x (cdr ls)) (cons (car ls) null))
        )
    ))

(define rovescio ;val: lista
  (lambda (ls) ;ls: lista
    (rovescio-rec ls null)
    ))

(define rovescio-rec ;val: lista
  (lambda (ls rv) ;ls,rv: lista
    (if (null? ls)
        rv
        (rovescio-rec (cdr ls) (cons (car ls) rv))
        )
    ))

(define lunghezza ;val: intero
  (lambda (ls) ;ls: lista
    (lunghezza-rec ls 0)
    ))

(define lunghezza-rec
  (lambda (ls k)
    (if (null? ls)
        k
        (lunghezza-rec (cdr ls) (+ k 1))
        )
    ))