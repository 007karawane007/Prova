;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname liste) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "drawings (1).ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "drawings (1).ss" "installed-teachpacks")) #f)))
(define belong? ;val booleano
  (lambda (n ls)  ;ls: lista numerica n: intero
    (if (null? ls)
        false
        (if(= n (car ls))
           true
           (belong? n (cdr ls))
        )
    )
    ))

(define position ;val intero
  (lambda (n ls) ;ls: lista numerica n: intero
    (let ((k 0)
          )
      (position-rec k n ls)
      )
    ))

(define position-rec
  (lambda (k n ls)
    (if (= n (car ls))
        k
        (position-rec (+ k 1) n (cdr ls))
        )
    ))

(define sorted-ins ;val lista
  (lambda (n ls)   ;ls: lista numerica n: intero
    (if (null? ls)
        (cons n ls)
        (cond ((> n (car ls))
               (cons (car ls) (sorted-ins n (cdr ls))))
              ((< n (car ls))
               (cons n ls))
              (else
               ls)
              )
        )
    ))

(define sorted-list-rec ;val lista
  (lambda (ls)
    (if (null? ls)
        ls
        (if (not (null? (cdr ls)))
        (if (> (car ls) (car (cdr ls)))
            (sorted-list-rec (cons (car (cdr ls)) (cons (car ls) (cdr (cdr ls)))))
            (cons (car ls) (sorted-list-rec (cdr ls)))
            )
        ls
        )
        )
    ))

(define sorted-list
  (lambda (ls)
    (if (not (ordinato? ls))
           (sorted-list (sorted-list-rec ls))
           ls
           )
    ))

(define ordinato?
  (lambda (ls)
    (if (not (null? (cdr ls)))
    (if (> (car ls) (car (cdr ls)))
        false
        (ordinato? (cdr ls))
        )
    true
    )
    ))
        
;(sorted-list '(1 2 3 4))
;(sorted-list '(35 8 41 24 7))
;(sorted-list '(8 35 24 7 41))
;(sorted-list '(8 24 7 35 41))
;(sorted-list '(8 7 24 35 41))
